package com.example.GroupProject.response;

import java.time.LocalDate;
import java.util.List;

import com.example.GroupProject.dto.ReservationAndTableByDate;

public class ReservationAndTableByDateRes extends BasicRes {

	private LocalDate reservationDate;
	public List<ReservationAndTableByDate> reservationAndTableByDateList;
	
	public ReservationAndTableByDateRes() {
		super();
	}
	public ReservationAndTableByDateRes(int code, String message) {
		super(code, message);
	}
	
	public ReservationAndTableByDateRes(int code, String message, LocalDate reservationDate,
			List<ReservationAndTableByDate> reservationAndTableByDateList) {
		super(code, message);
		this.reservationDate = reservationDate;
		this.reservationAndTableByDateList = reservationAndTableByDateList;
	}
	
	public LocalDate getReservationDate() {
		return reservationDate;
	}
	public void setReservationDate(LocalDate reservationDate) {
		this.reservationDate = reservationDate;
	}
	public List<ReservationAndTableByDate> getReservationAndTableByDateList() {
		return reservationAndTableByDateList;
	}
	public void setReservationAndTableByDateList(List<ReservationAndTableByDate> reservationAndTableByDateList) {
		this.reservationAndTableByDateList = reservationAndTableByDateList;
	}

}
