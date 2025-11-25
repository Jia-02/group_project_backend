package com.example.GroupProject.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservationAndTableByDate {

	//訂位資訊
    private LocalDate reservationDate;   // DATE 預約日期
    
    private String reservationPhone;     // VAR(45) 預約電話
    
    private LocalTime reservationTime;   // TIME 預約時間
    
    private String reservationName;      // VAR(100) 預約人姓名
    
    private int reservationCount;    // INT 用餐人數
    
    private int reservationAdultCount;    // INT 用餐大人人數
    
    private int reservationChildCount;    // INT 用餐小孩人數
    
    private boolean reservationStatus;      // TINYINT 預約狀態
    
    private String reservationNote;      // VAR(200) 備註
    
    private int childSeat;    // INT 兒童座椅數量
    
    //桌位資訊
    
	private String tableId;

	private String tableStatus;

	private int capacity;

	public ReservationAndTableByDate() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReservationAndTableByDate(LocalDate reservationDate, String reservationPhone, LocalTime reservationTime,
			String reservationName, int reservationCount, int reservationAdultCount, int reservationChildCount,
			boolean reservationStatus, String reservationNote, int childSeat, String tableId, String tableStatus,
			int capacity) {
		super();
		this.reservationDate = reservationDate;
		this.reservationPhone = reservationPhone;
		this.reservationTime = reservationTime;
		this.reservationName = reservationName;
		this.reservationCount = reservationCount;
		this.reservationAdultCount = reservationAdultCount;
		this.reservationChildCount = reservationChildCount;
		this.reservationStatus = reservationStatus;
		this.reservationNote = reservationNote;
		this.childSeat = childSeat;
		this.tableId = tableId;
		this.tableStatus = tableStatus;
		this.capacity = capacity;
	}

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

	public int getChildSeat() {
		return childSeat;
	}

	public void setChildSeat(int childSeat) {
		this.childSeat = childSeat;
	}

	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	public String getTableStatus() {
		return tableStatus;
	}

	public void setTableStatus(String tableStatus) {
		this.tableStatus = tableStatus;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

}
