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

	@Min(value = 0, message = ConstantsMessage.TABLE_POSITION_ERROR)
	private int tablePositionX;

	@Min(value = 0, message = ConstantsMessage.TABLE_POSITION_ERROR)
	private int tablePositionY;

	@Min(value = 1, message = ConstantsMessage.TABLE_LENGTH_X_ERROR)
	private int lengthX;

	@Min(value = 1, message = ConstantsMessage.TABLE_LENGTH_Y_ERROR)
	private int lengthY;

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

	public int getLengthX() {
		return lengthX;
	}

	public void setLengthX(int lengthX) {
		this.lengthX = lengthX;
	}

	public int getLengthY() {
		return lengthY;
	}

	public void setLengthY(int lengthY) {
		this.lengthY = lengthY;
	}

}
