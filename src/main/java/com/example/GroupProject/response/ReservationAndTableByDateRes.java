package com.example.GroupProject.response;

import java.util.List;

import com.example.GroupProject.dto.ReservationAndTableByDate;

public class ReservationAndTableByDateRes extends BasicRes {

	public List<ReservationAndTableByDate> reservationAndTableByDateList;

	public ReservationAndTableByDateRes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReservationAndTableByDateRes(int code, String message) {
		super(code, message);
		// TODO Auto-generated constructor stub
	}

	public ReservationAndTableByDateRes(int code, String message,
			List<ReservationAndTableByDate> reservationAndTableByDateList) {
		super(code, message);
		this.reservationAndTableByDateList = reservationAndTableByDateList;
	}

	public List<ReservationAndTableByDate> getReservationAndTableByDateList() {
		return reservationAndTableByDateList;
	}

	public void setReservationAndTableByDateList(List<ReservationAndTableByDate> reservationAndTableByDateList) {
		this.reservationAndTableByDateList = reservationAndTableByDateList;
	}

}
