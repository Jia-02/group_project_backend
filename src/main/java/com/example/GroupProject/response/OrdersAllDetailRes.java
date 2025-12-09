package com.example.GroupProject.response;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.example.GroupProject.request.OrderDetailReq;

public class OrdersAllDetailRes extends BasicRes {
	
    private int ordersId;
    private String ordersType;
    private LocalDate ordersDate;
    private LocalTime ordersTime;
    private int totalPrice;
    private String paymentType;
    private boolean paid;
    private String ordersCode;
    private String customerName;
    private String customerPhone;
    private String customerAddress;
    private String tableId;
    
    private List<OrderDetailReq> orderDetailsList;

	public OrdersAllDetailRes() {
		super();
	}

	public OrdersAllDetailRes(int code, String message) {
		super(code, message);
	}

	public OrdersAllDetailRes(int code, String message, int ordersId, String ordersType, LocalDate ordersDate,
			LocalTime ordersTime, int totalPrice, String paymentType, boolean paid, String ordersCode,
			String customerName, String customerPhone, String customerAddress, String tableId,
			List<OrderDetailReq> orderDetailsList) {
		super(code, message);
		this.ordersId = ordersId;
		this.ordersType = ordersType;
		this.ordersDate = ordersDate;
		this.ordersTime = ordersTime;
		this.totalPrice = totalPrice;
		this.paymentType = paymentType;
		this.paid = paid;
		this.ordersCode = ordersCode;
		this.customerName = customerName;
		this.customerPhone = customerPhone;
		this.customerAddress = customerAddress;
		this.tableId = tableId;
		this.orderDetailsList = orderDetailsList;
	}

	public int getOrdersId() {
		return ordersId;
	}

	public void setOrdersId(int ordersId) {
		this.ordersId = ordersId;
	}

	public String getOrdersType() {
		return ordersType;
	}

	public void setOrdersType(String ordersType) {
		this.ordersType = ordersType;
	}

	public LocalDate getOrdersDate() {
		return ordersDate;
	}

	public void setOrdersDate(LocalDate ordersDate) {
		this.ordersDate = ordersDate;
	}

	public LocalTime getOrdersTime() {
		return ordersTime;
	}

	public void setOrdersTime(LocalTime ordersTime) {
		this.ordersTime = ordersTime;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public String getOrdersCode() {
		return ordersCode;
	}

	public void setOrdersCode(String ordersCode) {
		this.ordersCode = ordersCode;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	public List<OrderDetailReq> getOrderDetailsList() {
		return orderDetailsList;
	}

	public void setOrderDetailsList(List<OrderDetailReq> orderDetailsList) {
		this.orderDetailsList = orderDetailsList;
	}

    
    
}
