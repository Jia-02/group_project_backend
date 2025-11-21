package com.example.GroupProject.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservationDto {
	
    private LocalDate reservationDate;   // DATE 預約日期
    
    private String reservationPhone;     // VAR(45) 預約電話
    
    private LocalTime reservationTime;   // TIME 預約時間
    
    private String reservationName;      // VAR(100) 預約人姓名
    
    private int reservationCount;    // INT 用餐人數
    
    private int reservationAdultCount;    // INT 用餐大人人數
    
    private int reservationChildCount;    // INT 用餐小孩人數
    
    private boolean reservationStatus;      // TINYINT 預約狀態
    
    private String reservationNote;      // VAR(200) 備註
    
    private String tableId;              // VAR(20) 桌位編號

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

	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}
    

}
