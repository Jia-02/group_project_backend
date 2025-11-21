package com.example.GroupProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.GroupProject.dto.ReservationDto;
import com.example.GroupProject.response.BasicRes;
import com.example.GroupProject.service.ReservationService;

@CrossOrigin // 允許前端來源
@RestController
public class ReservationController {
	
	@Autowired
	private ReservationService reservationService;
	
    @PostMapping("/reservation/create")
    public BasicRes createReservation(@RequestBody ReservationDto reservationDto) {
        return reservationService.createReservation(reservationDto);
    }
	
}
