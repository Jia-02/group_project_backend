package com.example.GroupProject.dto;

import java.time.LocalTime;

public class MealStatusDto {

	private int mealStatusId;

	private String mealStatus;

	private int estimatedTime;

	private LocalTime finishTime;

	private int ordersId;

	public MealStatusDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MealStatusDto(String mealStatus, int estimatedTime, LocalTime finishTime, int ordersId) {
		super();
		this.mealStatus = mealStatus;
		this.estimatedTime = estimatedTime;
		this.finishTime = finishTime;
		this.ordersId = ordersId;
	}

	public int getMealStatusId() {
		return mealStatusId;
	}

	public void setMealStatusId(int mealStatusId) {
		this.mealStatusId = mealStatusId;
	}

	public String getMealStatus() {
		return mealStatus;
	}

	public void setMealStatus(String mealStatus) {
		this.mealStatus = mealStatus;
	}

	public int getEstimatedTime() {
		return estimatedTime;
	}

	public void setEstimatedTime(int estimatedTime) {
		this.estimatedTime = estimatedTime;
	}

	public LocalTime getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(LocalTime finishTime) {
		this.finishTime = finishTime;
	}

	public int getOrdersId() {
		return ordersId;
	}

	public void setOrdersId(int ordersId) {
		this.ordersId = ordersId;
	}

}
