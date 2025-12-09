package com.example.GroupProject.response;

import java.util.List;

import com.example.GroupProject.vo.OrdersVo;

public class OrdersListRes extends BasicRes {
	
	private List<OrdersVo> ordersList;

	public OrdersListRes() {
		super();
	}

	public OrdersListRes(int code, String message) {
		super(code, message);
	}

	public OrdersListRes(int code, String message, List<OrdersVo> ordersList) {
		super(code, message);
		this.ordersList = ordersList;
	}

	public List<OrdersVo> getOrdersList() {
		return ordersList;
	}

	public void setOrdersList(List<OrdersVo> ordersList) {
		this.ordersList = ordersList;
	}
}
