package com.example.GroupProject.vo;

import java.time.LocalTime;

public class ReservationVo {
    
    private String reservationPhone;     // VAR(45) 預約電話
    
    private LocalTime reservationTime;   // TIME 預約時間
    
    private String reservationName;      // VAR(100) 預約人姓名
    
    private int reservationCount;    // INT 用餐人數
    
    private int reservationAdultCount;    // INT 用餐大人人數
    
    private int reservationChildCount;    // INT 用餐小孩人數
    
    private boolean reservationStatus;      // TINYINT 預約狀態
    
    private String reservationNote;      // VAR(200) 備註
    
    private int childSeat;    // INT 安全座椅數量

	public String getReservationPhone() {
		return reservationPhone;
	}

	public void setReservationPhone(String reservationPhone) {
		this.reservationPhone = reservationPhone;
	}

	public LocalTime getReservationTime() {
		return reservationTime;
	}

	public void setReservationTime(LocalTime reservationTime) {
		this.reservationTime = reservationTime;
	}

	public String getReservationName() {
		return reservationName;
	}

	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}

	public int getReservationCount() {
		return reservationCount;
	}

	public void setReservationCount(int reservationCount) {
		this.reservationCount = reservationCount;
	}

	public int getReservationAdultCount() {
		return reservationAdultCount;
	}

	public void setReservationAdultCount(int reservationAdultCount) {
		this.reservationAdultCount = reservationAdultCount;
	}

	public int getReservationChildCount() {
		return reservationChildCount;
	}

	public void setReservationChildCount(int reservationChildCount) {
		this.reservationChildCount = reservationChildCount;
	}

	public boolean isReservationStatus() {
		return reservationStatus;
	}

	public void setReservationStatus(boolean reservationStatus) {
		this.reservationStatus = reservationStatus;
	}

	public String getReservationNote() {
		return reservationNote;
	}

	public void setReservationNote(String reservationNote) {
		this.reservationNote = reservationNote;
	}

	public int getChildSeat() {
		return childSeat;
	}

	public void setChildSeat(int childSeat) {
		this.childSeat = childSeat;
	}
    
}
