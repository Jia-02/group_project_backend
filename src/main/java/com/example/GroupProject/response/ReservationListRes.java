package com.example.GroupProject.response;

import java.util.List;

import com.example.GroupProject.dto.ReservationDto;

public class ReservationListRes extends BasicRes {

	private List<ReservationDto> reservationList;

	public ReservationListRes() {
		super();
	}

	public ReservationListRes(int code, String message) {
		super(code, message);
	}

	public ReservationListRes(int code, String message, List<ReservationDto> reservationList) {
		super(code, message);
		this.reservationList = reservationList;
	}

	public List<ReservationDto> getReservationList() {
		return reservationList;
	}

	public void setReservationList(List<ReservationDto> reservationList) {
		this.reservationList = reservationList;
	}
	
}
