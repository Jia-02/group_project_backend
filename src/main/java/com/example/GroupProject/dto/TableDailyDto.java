package com.example.GroupProject.dto;

import java.time.LocalDate;

public class TableDailyDto {
	
    private LocalDate tableDailyDate;
    private String tableId;
    private boolean tableDailyStatus;
    
	public LocalDate getTableDailyDate() {
		return tableDailyDate;
	}
	public void setTableDailyDate(LocalDate tableDailyDate) {
		this.tableDailyDate = tableDailyDate;
	}
	public String getTableId() {
		return tableId;
	}
	public void setTableId(String tableId) {
		this.tableId = tableId;
	}
	public boolean isTableDailyStatus() {
		return tableDailyStatus;
	}
	public void setTableDailyStatus(boolean tableDailyStatus) {
		this.tableDailyStatus = tableDailyStatus;
	}

}
