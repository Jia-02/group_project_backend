package com.example.GroupProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.GroupProject.constants.ResCodeMessage;
import com.example.GroupProject.dao.ReservationDao;
import com.example.GroupProject.dto.ReservationDto;
import com.example.GroupProject.response.BasicRes;

@Service
public class ReservationService {

	@Autowired
	private ReservationDao reservationDao;

	/**
	 * 新增訂位
	 */
	@Transactional(rollbackFor = Exception.class)
	public BasicRes createReservation(ReservationDto reservationDto){
		// 檢查總人數、大人至少1個，大人+小孩=總人數
		if (reservationDto.getReservationCount() <= 0 //
				|| reservationDto.getReservationAdultCount() <= 0 //
				|| reservationDto.getReservationAdultCount()
						+ reservationDto.getReservationChildCount() != reservationDto.getReservationCount()) {
			return new BasicRes(// 人數輸入錯誤
					ResCodeMessage.PEOPLE_COUNT_FAILED.getCode(), ResCodeMessage.PEOPLE_COUNT_FAILED.getMessage());
		}

        // 檢查桌位是否已被預約、使用中?
	

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

}
