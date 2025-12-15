package com.example.GroupProject.response;

public class OrderBasicRes extends BasicRes {
	
	private int ordersId;

	public OrderBasicRes() {
		super();
	}

	public OrderBasicRes(int code, String message) {
		super(code, message);
	}

	public OrderBasicRes(int code, String message, int ordersId) {
		super(code, message);
		this.ordersId = ordersId;
	}

	public int getOrdersId() {
		return ordersId;
	}

	public void setOrdersId(int ordersId) {
		this.ordersId = ordersId;
	}
}
