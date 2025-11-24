package com.example.GroupProject.request;

import java.time.LocalDate;

public class ReservationDeleteReq {
	
    private LocalDate reservationDate;
    
    private String reservationPhone;
    
	public LocalDate getReservationDate() {
		return reservationDate;
	}
	public void setReservationDate(LocalDate reservationDate) {
		this.reservationDate = reservationDate;
	}
	public String getReservationPhone() {
		return reservationPhone;
	}
	public void setReservationPhone(String reservationPhone) {
		this.reservationPhone = reservationPhone;
	}
    
    
}
