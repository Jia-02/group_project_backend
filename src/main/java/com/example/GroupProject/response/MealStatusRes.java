package com.example.GroupProject.response;

import java.util.List;

import com.example.GroupProject.dto.MealStatusDto;
import com.example.GroupProject.dto.OrdersDto;
import com.example.GroupProject.request.OrderDetailReq;

public class MealStatusRes extends BasicRes {

	private MealStatusDto mealStatus;

	private OrdersDto order;

	private List<OrderDetailReq> orderDetailsList;

	public MealStatusRes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MealStatusRes(int code, String message, MealStatusDto mealStatus, OrdersDto order,
			List<OrderDetailReq> orderDetailsList) {
		super(code, message);
		this.mealStatus = mealStatus;
		this.order = order;
		this.orderDetailsList = orderDetailsList;
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

	public List<OrderDetailReq> getOrderDetailsList() {
		return orderDetailsList;
	}

	public void setOrderDetailsList(List<OrderDetailReq> orderDetailsList) {
		this.orderDetailsList = orderDetailsList;
	}

}
