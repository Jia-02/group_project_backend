package com.example.GroupProject.response;

import com.example.GroupProject.dto.MealStatusDto;

public class MealStatusRes extends BasicRes{
	
	private MealStatusDto mealStatus;
	
	

	public MealStatusRes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MealStatusRes(int code, String message) {
		super(code, message);
		// TODO Auto-generated constructor stub
	}
	
	public MealStatusRes(int code, String message, MealStatusDto mealStatus) {
		super(code, message);
		this.mealStatus = mealStatus;
	}

	public MealStatusDto getMealStatus() {
		return mealStatus;
	}

	public void setMealStatus(MealStatusDto mealStatus) {
		this.mealStatus = mealStatus;
	}
	
}
