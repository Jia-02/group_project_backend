package com.example.GroupProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.GroupProject.dto.ReservationDto;
import com.example.GroupProject.request.ReservationDeleteReq;
import com.example.GroupProject.request.ReservationUpdateReq;
import com.example.GroupProject.response.BasicRes;
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
