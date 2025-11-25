package com.example.GroupProject.service;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.GroupProject.constants.ResCodeMessage;
import com.example.GroupProject.dao.ReservationDao;
import com.example.GroupProject.dao.TablesDao;
import com.example.GroupProject.dto.ReservationDto;
import com.example.GroupProject.request.ReservationUpdateReq;
import com.example.GroupProject.response.BasicRes;
import com.example.GroupProject.response.ReservationAndTableByDateRes;
import com.example.GroupProject.response.ReservationListRes;

@Service
public class ReservationService {

	@Autowired
	private ReservationDao reservationDao;

	@Autowired
	private TablesDao tableDao;

	/** 新增訂位 */
	@Transactional(rollbackFor = Exception.class)
	public BasicRes createReservation(ReservationDto reservationDto) {

		// 取得日期、時間、桌位
		LocalDate date = reservationDto.getReservationDate();
		LocalTime time = reservationDto.getReservationTime();
		String tableId = reservationDto.getTableId();
		String phone = reservationDto.getReservationPhone();
		int repeatCount = reservationDao.countByDateAndPhone(date, phone);
		int childSeat = reservationDto.getChildSeat();

		// 客人是否當日重複預約
		if (repeatCount > 0) {
			return new BasicRes( // 當日已預約過
					ResCodeMessage.PHONE_IS_RESERVATION_IN_DATE.getCode(), //
					ResCodeMessage.PHONE_IS_RESERVATION_IN_DATE.getMessage());
		}
		
		//檢查兒童座椅數量
		final int maxChildSeat = 5; // 店家總庫存數
		if(childSeat > 0) {
			int reservedSeats = reservationDao.sumChildSeatsByDateAndTime(date, time);
			System.out.println(reservedSeats);
			if (childSeat + reservedSeats > maxChildSeat) {
                return new BasicRes(
    					ResCodeMessage.CHILD_SEAT_INSUFFICIENT.getCode(), //
    					ResCodeMessage.CHILD_SEAT_INSUFFICIENT.getMessage());
		}}

		// 檢查總人數、大人至少1個，大人+小孩=總人數
		if (reservationDto.getReservationCount() <= 0 //
				|| reservationDto.getReservationAdultCount() <= 0 //
				|| reservationDto.getReservationAdultCount()
						+ reservationDto.getReservationChildCount() != reservationDto.getReservationCount()) {
			return new BasicRes(// 人數輸入錯誤
					ResCodeMessage.PEOPLE_COUNT_FAILED.getCode(), //
					ResCodeMessage.PEOPLE_COUNT_FAILED.getMessage());
		}

		// 檢查桌位是否存在
		if (!tableDao.existsById(reservationDto.getTableId())) { //
			return new BasicRes(ResCodeMessage.TABLE_NOT_FOUND.getCode(), //
					ResCodeMessage.TABLE_NOT_FOUND.getMessage());
		}

		// 檢查桌位容納數量 > 用餐人數
		int tableCapacity = tableDao.getTableCapacityById(reservationDto.getTableId());
		if (tableCapacity == 0 || reservationDto.getReservationCount() > tableCapacity) {
			return new BasicRes( // 桌位容量不足
					ResCodeMessage.TABLE_CAPACITY_INSUFFICIENT.getCode(),
					ResCodeMessage.TABLE_CAPACITY_INSUFFICIENT.getMessage());
		}

		// 檢查同個時間，同個桌位無法被重複選擇
		if (reservationDao.isTableReservedAtSameTime(tableId, date, time)) {
			return new BasicRes( //
					ResCodeMessage.TABLE_IS_RESERVATION.getCode(),
					ResCodeMessage.TABLE_IS_RESERVATION.getMessage());
		}

		// 新增訂位
		int result = reservationDao.createReservation(reservationDto);
		if (result > 0) {
			return new BasicRes(//
					ResCodeMessage.SUCCESS.getCode(), //
					ResCodeMessage.SUCCESS.getMessage());
		} else {
			return new BasicRes(//
					ResCodeMessage.CREATE_RESERVATION_FAILED.getCode(), //
					ResCodeMessage.CREATE_RESERVATION_FAILED.getMessage());
		}
	}

	/** 刪除訂位 */
	@Transactional(rollbackFor = Exception.class)
	public BasicRes deleteReservation(LocalDate reservationDate, String reservationPhone) {
		int result = reservationDao.deleteReservation(reservationDate, reservationPhone);
		if (result > 0) {
			return new BasicRes(ResCodeMessage.SUCCESS.getCode(), //
					ResCodeMessage.SUCCESS.getMessage());
		} else {
			return new BasicRes(ResCodeMessage.DELETE_RESERVATION_FAILED.getCode(), //
					ResCodeMessage.DELETE_RESERVATION_FAILED.getMessage());
		}
	}

	/** 編輯訂位 (採用刪除舊記錄，再新增新記錄的方式) */
	@Transactional(rollbackFor = Exception.class)
	public BasicRes updateReservation(ReservationUpdateReq req) {

		// 參數
		String phone = req.getReservationPhone();
		LocalDate oldDate = req.getReservationDate();
		LocalDate newDate = req.getNewDate();

		// 查詢並刪除舊訂位 -透過手機和日期查詢現有的訂位記錄
		ReservationDto existingReservation = reservationDao.findByDateAndPhone(oldDate, phone);
		if (existingReservation == null) {
			return new BasicRes(ResCodeMessage.RESERVATION_NOT_FOUND.getCode(),
					ResCodeMessage.RESERVATION_NOT_FOUND.getMessage());
		}

		// 刪除舊訂位記錄
		int deleteResult = reservationDao.deleteReservation(oldDate, phone);
		if (deleteResult == 0) {
			return new BasicRes(ResCodeMessage.NOT_FOUND.getCode(), ResCodeMessage.NOT_FOUND.getMessage());
		}

		// 新增完整的新訂位記錄
		// 將 DTO 內的 ReservationDate 屬性設置為更新後的日期 (newDate)
		req.setReservationDate(newDate);
		BasicRes createRes = this.createReservation(req);
		// 檢查新增是否成功，如果失敗，則整個交易將回滾
		if (createRes.getCode() != ResCodeMessage.SUCCESS.getCode()) {
			// 拋出一個 Runt2imeException
	        throw new RuntimeException(createRes.getCode() + createRes.getMessage());
		}
		return createRes;
	}
	/** 查詢全部訂位列表 */
	@Transactional(readOnly = true)
	public ReservationListRes getReservationList() {
		return new ReservationListRes(//
				ResCodeMessage.SUCCESS.getCode(), //
				ResCodeMessage.SUCCESS.getMessage(), //
				reservationDao.getReservationList());
	}
	
	/** 查詢一天的訂位資料(含桌位) */
	@Transactional(rollbackFor = Exception.class)
	public ReservationAndTableByDateRes findReservationsByDate(LocalDate reservationDate) {
		return new ReservationAndTableByDateRes(//
				ResCodeMessage.SUCCESS.getCode(), //
				ResCodeMessage.SUCCESS.getMessage(), //
				reservationDao.findReservationsByDate(reservationDate));
	}
	
	/** 查詢同一天某時段之資訊桌位、預約資訊 */
	@Transactional(rollbackFor = Exception.class)
	public ReservationAndTableByDateRes findTableStatusByTimeSlot(LocalDate reservationDate, //
			LocalTime reservationTime) {
		return new ReservationAndTableByDateRes(//
				ResCodeMessage.SUCCESS.getCode(), //
				ResCodeMessage.SUCCESS.getMessage(), //
				reservationDao.findTableStatusByTimeSlot(reservationDate,reservationTime));
	}
}
