package com.example.GroupProject.request;

import java.time.LocalDate;

import com.example.GroupProject.dto.ReservationDto;

public class ReservationUpdateReq extends ReservationDto {
	
	private LocalDate newDate;

	public ReservationUpdateReq() {
		super();
	}

	public ReservationUpdateReq(LocalDate newDate) {
		super();
		this.newDate = newDate;
	}

	public LocalDate getNewDate() {
		return newDate;
	}

	public void setNewDate(LocalDate newDate) {
		this.newDate = newDate;
	}


	
}
