package com.example.GroupProject.vo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class OrdersMealListVo {
	
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
    
    private List<OrdersMealVo> orderDetailsList;

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

	public List<OrdersMealVo> getOrderDetailsList() {
		return orderDetailsList;
	}

	public void setOrderDetailsList(List<OrdersMealVo> orderDetailsList) {
		this.orderDetailsList = orderDetailsList;
	}
    
    

}
