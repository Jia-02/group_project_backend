package com.example.GroupProject.vo;

import java.time.LocalDate;
import java.time.LocalTime;

public class OrdersVo {

	// 訂單基本資訊
	private int ordersId; //流水id
    private String ordersType;      // 內用/外帶/外送
    private LocalDate ordersDate;   // 2025-12-05
    private LocalTime ordersTime;   // 12:00:00
    private String paymentType;     // 現金 / 線上支付 /信用卡
    private boolean paid;           // 是否付款
    private String ordersCode;      // 2512051200A01 -後端生成
    
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

}
