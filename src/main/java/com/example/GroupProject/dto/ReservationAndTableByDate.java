package com.example.GroupProject.dto;

import java.util.List;

public class ReservationAndTableByDate {

	// 共同資訊
    private String tableId;
    private int capacity;
    private boolean tableDailyStatus; // 當天是否開放
    private List<ReservationDto> reservations; // 訂位列表
    
    
	public ReservationAndTableByDate() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReservationAndTableByDate(String tableId, int capacity, boolean tableDailyStatus,
			List<ReservationDto> reservations) {
		super();
		this.tableId = tableId;
		this.capacity = capacity;
		this.tableDailyStatus = tableDailyStatus;
		this.reservations = reservations;
	}
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
	public boolean isTableDailyStatus() {
		return tableDailyStatus;
	}
	public void setTableDailyStatus(boolean tableDailyStatus) {
		this.tableDailyStatus = tableDailyStatus;
	}
	public List<ReservationDto> getReservations() {
		return reservations;
	}
	public void setReservations(List<ReservationDto> reservations) {
		this.reservations = reservations;
	}

}
