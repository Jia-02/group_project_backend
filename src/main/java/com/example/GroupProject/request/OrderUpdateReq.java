package com.example.GroupProject.request;

import java.util.List;

public class OrderUpdateReq {
	
    private int ordersId;
    private List<OrderDetailReq> orderDetails;
    
	public OrderUpdateReq() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderUpdateReq(int ordersId, List<OrderDetailReq> orderDetails) {
		super();
		this.ordersId = ordersId;
		this.orderDetails = orderDetails;
	}
	public int getOrdersId() {
		return ordersId;
	}
	public void setOrdersId(int ordersId) {
		this.ordersId = ordersId;
	}
	public List<OrderDetailReq> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(List<OrderDetailReq> orderDetails) {
		this.orderDetails = orderDetails;
	}

}
