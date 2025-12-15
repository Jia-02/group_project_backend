package com.example.GroupProject.response;

import com.example.GroupProject.dto.MealStatusDto;
import com.example.GroupProject.dto.OrdersDto;

public class MealStatusRes extends BasicRes {

	private MealStatusDto mealStatus;

	private OrdersDto order;

	public MealStatusRes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MealStatusRes(int code, String message, MealStatusDto mealStatus, OrdersDto order) {
		super(code, message);
		this.mealStatus = mealStatus;
		this.order = order;
	}

	public MealStatusRes(int code, String message) {
		super(code, message);
		// TODO Auto-generated constructor stub
	}

	public MealStatusDto getMealStatus() {
		return mealStatus;
	}

	public void setMealStatus(MealStatusDto mealStatus) {
		this.mealStatus = mealStatus;
	}

	public OrdersDto getOrder() {
		return order;
	}

	public void setOrder(OrdersDto order) {
		this.order = order;
	}

}
