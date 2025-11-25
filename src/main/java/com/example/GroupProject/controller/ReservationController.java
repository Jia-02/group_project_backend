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
import com.example.GroupProject.response.ReservationListRes;
import com.example.GroupProject.service.ReservationService;

import jakarta.validation.Valid;

@CrossOrigin // 允許前端來源
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
	public ReservationListRes getTableList() {
		return reservationService.getReservationList();
	}
	
    //顯示一天的訂位資料(含桌位)
	@GetMapping(value = "reservation/date_list")
	public ReservationAndTableByDateRes findReservationsByDate(//
			@RequestParam("reservation_date") LocalDate reservationDate) {
		return reservationService.findReservationsByDate(reservationDate);
	}
	
    //查詢同一天某時段之資訊桌位、預約資訊 
	@GetMapping(value = "reservation/time_list")
	public ReservationAndTableByDateRes findTableStatusByTimeSlot(//
			@RequestParam("reservation_date") LocalDate reservationDate, //
			@RequestParam("reservation_time") LocalTime reservationTime
			) {
		return reservationService.findTableStatusByTimeSlot(reservationDate, reservationTime);
	}
	
	//刪除訂位
    @PostMapping("reservation/delete")
    public BasicRes deleteReservation(@Valid @RequestBody ReservationDeleteReq req) {
        return reservationService.deleteReservation(req.getReservationDate(),req.getReservationPhone());
    }
    
    //更新訂位
    @PostMapping("reservation/update")
    public BasicRes updateReservation(@RequestBody ReservationUpdateReq req) {
        return reservationService.updateReservation(req);
    }
	
}
