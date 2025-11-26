package com.example.GroupProject.response;

import java.util.List;

import com.example.GroupProject.dto.ReservationAndTableByTime;

public class ReservationAndTableByTimeRes extends BasicRes {
	
	private List<ReservationAndTableByTime> reservationAndTableByTimeList;

	public ReservationAndTableByTimeRes() {
		super();
	}

	public ReservationAndTableByTimeRes(int code, String message) {
		super(code, message);
	}

	public ReservationAndTableByTimeRes(int code, String message,
			List<ReservationAndTableByTime> reservationAndTableByTimeList) {
		super(code, message);
		this.reservationAndTableByTimeList = reservationAndTableByTimeList;
	}

	public List<ReservationAndTableByTime> getReservationAndTableByTimeList() {
		return reservationAndTableByTimeList;
	}

	public void setReservationAndTableByTimeList(List<ReservationAndTableByTime> reservationAndTableByTimeList) {
		this.reservationAndTableByTimeList = reservationAndTableByTimeList;
	}

}
