package com.example.GroupProject.controller;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.GroupProject.dto.ReservationDto;
import com.example.GroupProject.request.ReservationDeleteReq;
import com.example.GroupProject.request.ReservationUpdateReq;
import com.example.GroupProject.response.BasicRes;
import com.example.GroupProject.response.ReservationAndTableByDateRes;
import com.example.GroupProject.response.ReservationAndTableByTimeRes;
import com.example.GroupProject.response.ReservationListRes;
import com.example.GroupProject.service.ReservationService;

@CrossOrigin
@RestController
public class ReservationController {
	
	@Autowired
	private ReservationService reservationService;
	
	//新增訂位
    @PostMapping("reservation/create")
    public BasicRes createReservation(@RequestBody ReservationDto reservationDto) {
        return reservationService.createReservation(reservationDto);
    }
    
    //顯示所有訂位
	@GetMapping(value = "reservation/list")
	public ReservationListRes getReservationList() {
		return reservationService.getReservationList();
	}
	
	//根據日期、手機顯示單筆資料
	
    //顯示一天的訂位資料(含桌位、狀態)
	@GetMapping(value = "reservation/date_list")
	public ReservationAndTableByDateRes findReservationsByDate(//
			@RequestParam("reservationDate") LocalDate reservationDate) {
		return reservationService.findReservationsByDate(reservationDate);
	}
	
    //查詢同一天某時段之資訊桌位、預約資訊 
	@GetMapping(value = "reservation/time_list")
	public ReservationAndTableByTimeRes findTableStatusByTimeSlot(//
			@RequestParam("reservationDate") LocalDate reservationDate, //
			@RequestParam("reservationTime") LocalTime reservationTime
			) {
		return reservationService.findTableStatusByTimeSlot(reservationDate, reservationTime);
	}
	
    //查詢當下資訊桌位、預約資訊 
	@GetMapping(value = "reservation/now_time_list")
	public ReservationAndTableByTimeRes findTableStatusByNow() {
		return reservationService.findTableStatusByNow();
	}
	
	//刪除訂位
    @PostMapping("reservation/delete")
    public BasicRes deleteReservation(@RequestBody ReservationDeleteReq req) {
        return reservationService.deleteReservation(req.getReservationDate(),req.getReservationPhone());
    }
    
    //更新訂位
    @PostMapping("reservation/update")
    public BasicRes updateReservation(@RequestBody ReservationUpdateReq req) {
        return reservationService.updateReservation(req);
    }
	
}
