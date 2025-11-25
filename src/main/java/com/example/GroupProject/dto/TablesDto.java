package com.example.GroupProject.dto;

import com.example.GroupProject.constants.ConstantsMessage;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class TablesDto {

	@NotBlank(message = ConstantsMessage.TABLE_ID_ERROR)
	private String tableId;

	@NotBlank(message = ConstantsMessage.TABLE_STATUS_ERROR)
	private String tableStatus;
	
	@Min(value = 2, message = ConstantsMessage.TABLE_CAPACITY_ERROR)
	private int tableCapacity;

	// 由於畫布設定暫且為500*500 且桌位大小為20*20 所以 x軸座標不能小於0 大於480
	@Min(value = 0, message = ConstantsMessage.TABLE_POSITION_ERROR)
	@Max(value = 480, message = ConstantsMessage.TABLE_POSITION_ERROR)
	private int tablePositionX;
	
	//	由於畫布設定暫且為500*500 且桌位大小為20*20 所以 y軸座標不能小於0 大於480
	@Min(value = 0, message = ConstantsMessage.TABLE_POSITION_ERROR)
	@Max(value = 480, message = ConstantsMessage.TABLE_POSITION_ERROR)
	private int tablePositionY;

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

	public int getTableCapacity() {
		return tableCapacity;
	}

	public void setTableCapacity(int tableCapacity) {
		this.tableCapacity = tableCapacity;
	}

	public int getTablePositionX() {
		return tablePositionX;
	}

	public void setTablePositionX(int tablePositionX) {
		this.tablePositionX = tablePositionX;
	}

	public int getTablePositionY() {
		return tablePositionY;
	}

	public void setTablePositionY(int tablePositionY) {
		this.tablePositionY = tablePositionY;
	}

}
