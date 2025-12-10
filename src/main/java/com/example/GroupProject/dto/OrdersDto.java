package com.example.GroupProject.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class OrdersDto {
	
	// 訂單基本資訊
	private int ordersId; //流水id
    private String ordersType;      // 內用/外帶/外送
    private LocalDate ordersDate;   // 2025-12-05
    private LocalTime ordersTime;   // 12:00:00
    private int totalPrice;
    private String paymentType;     // 現金 / 線上支付 /信用卡
    private boolean paid;           // 是否付款
    private String ordersCode;      // 2512051200A01 -後端生成

    // 顧客資訊（外帶外送）
    private String customerName;
    private String customerPhone;
    private String customerAddress;
    
    private String tableId;         // A01（內用才有）

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

}
