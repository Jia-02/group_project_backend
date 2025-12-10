package com.example.GroupProject.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DeliveryTask {

	private String orderNo;
	
	private int deliveryId;
	
	private LocalDate date;
	
	private BigDecimal distanceKm;
	
	private String status;
	
	private boolean isReceiveMoney;
	
	private BigDecimal money;
	
	

	public DeliveryTask() {
		super();
	}
	

	public DeliveryTask(String orderNo, int deliveryId, LocalDate date, BigDecimal distanceKm, String status,
			boolean isReceiveMoney, BigDecimal money) {
		super();
		this.orderNo = orderNo;
		this.deliveryId = deliveryId;
		this.date = date;
		this.distanceKm = distanceKm;
		this.status = status;
		this.isReceiveMoney = isReceiveMoney;
		this.money = money;
	}



	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public int getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(int deliveryId) {
		this.deliveryId = deliveryId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public BigDecimal getDistanceKm() {
		return distanceKm;
	}

	public void setDistanceKm(BigDecimal distanceKm) {
		this.distanceKm = distanceKm;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isReceiveMoney() {
		return isReceiveMoney;
	}

	public void setReceiveMoney(boolean isReceiveMoney) {
		this.isReceiveMoney = isReceiveMoney;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	
	
	
}

