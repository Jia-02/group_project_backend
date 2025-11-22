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
import com.example.GroupProject.response.BasicRes;
import com.example.GroupProject.response.ReservationListRes;

@Service
public class ReservationService {

	@Autowired
	private ReservationDao reservationDao;

	@Autowired
	private TablesDao tableDao;
	
	/**
	 * 查詢全部訂位列表
	 */
	@Transactional(readOnly = true)
	public ReservationListRes getReservationList() {
		return new ReservationListRes(//
				ResCodeMessage.SUCCESS.getCode(), //
				ResCodeMessage.SUCCESS.getMessage(), //
				reservationDao.getReservationList());
	}

	
	/**
	 * 新增訂位
	 */
	@Transactional(rollbackFor = Exception.class)
	public BasicRes createReservation(ReservationDto reservationDto) {
		
		// 取得日期、時間、桌位
		LocalDate date = reservationDto.getReservationDate();
		LocalTime time = reservationDto.getReservationTime();
		String tableId = reservationDto.getTableId();
	    String phone = reservationDto.getReservationPhone();
	    int repeatCount = reservationDao.countByDateAndPhone(date, phone);
		
		//客人是否當日重複預約
	    if (repeatCount > 0) {			
	    	return new BasicRes( //當日已預約過
				ResCodeMessage.PHONE_IS_RESERVATION_IN_DATE.getCode(), //
				ResCodeMessage.PHONE_IS_RESERVATION_IN_DATE.getMessage());
	    }
		
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
		if (!tableDao.existsById(reservationDto.getTableId())) {
			return new BasicRes(ResCodeMessage.TABLE_NOT_FOUND.getCode(), ResCodeMessage.TABLE_NOT_FOUND.getMessage());
		}

		// 檢查同個時間，同個桌位無法被重複選擇
		if (reservationDao.isTableReservedAtSameTime(tableId, date, time)) {
			return new BasicRes(ResCodeMessage.TABLE_IS_RESERVATION.getCode(),
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
	
	
	//刪除訂位
    @Transactional(rollbackFor = Exception.class)
    public BasicRes deleteReservation(LocalDate reservationDate, String reservationPhone) {
        int result = reservationDao.deleteReservation(reservationDate,reservationPhone);
        if (result > 0) {
            return new BasicRes(ResCodeMessage.SUCCESS.getCode(), //
            		ResCodeMessage.SUCCESS.getMessage());
        } else {
            return new BasicRes(ResCodeMessage.DELETE_RESERVATION_FAILED.getCode(), //
            		ResCodeMessage.DELETE_RESERVATION_FAILED.getMessage());
        }
    }

}
