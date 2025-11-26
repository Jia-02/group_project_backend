package com.example.GroupProject.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservationAndTableByTime {
	
	// --- 桌位---
    private String tableId; // t.table_id
    private int capacity;   // t.capacity
    private String tableStatus; // 狀態字串，如 '使用中', '已預約', '可預約','未開放' 等

    // --- 桌位當日狀態 (來自 table_daily d) ---
    private boolean tableDailyStatus;  //0是未開放 1是開放

    // --- 訂位資訊 (來自 reservation r) ---
    private LocalDate reservationDate;      // r.reservation_date
    private LocalTime reservationTime;      // r.reservation_time
    private String reservationPhone;        // r.reservation_phone
    private String reservationName;         // r.reservation_name
    private int reservationCount;           // r.reservation_count
    private int reservationAdultCount;      // r.reservation_adult_count
    private int reservationChildCount;      // r.reservation_child_count
    private boolean reservationStatus;      // r.reservation_status
    private int childSeat;                  // r.child_seat
    private String reservationNote;         // r.reservation_note
    
	public String getTableId() {
		return tableId;
	}
	public void setTableId(String tableId) {
		this.tableId = tableId;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public String getTableStatus() {
		return tableStatus;
	}
	public void setTableStatus(String tableStatus) {
		this.tableStatus = tableStatus;
	}
	public boolean isTableDailyStatus() {
		return tableDailyStatus;
	}
	public void setTableDailyStatus(boolean tableDailyStatus) {
		this.tableDailyStatus = tableDailyStatus;
	}
	public LocalDate getReservationDate() {
		return reservationDate;
	}
	public void setReservationDate(LocalDate reservationDate) {
		this.reservationDate = reservationDate;
	}
	public LocalTime getReservationTime() {
		return reservationTime;
	}
	public void setReservationTime(LocalTime reservationTime) {
		this.reservationTime = reservationTime;
	}
	public String getReservationPhone() {
		return reservationPhone;
	}
	public void setReservationPhone(String reservationPhone) {
		this.reservationPhone = reservationPhone;
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
	public int getChildSeat() {
		return childSeat;
	}
	public void setChildSeat(int childSeat) {
		this.childSeat = childSeat;
	}
	public String getReservationNote() {
		return reservationNote;
	}
	public void setReservationNote(String reservationNote) {
		this.reservationNote = reservationNote;
	}

}
