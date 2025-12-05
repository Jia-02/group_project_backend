package com.example.GroupProject.dto;

public class OrdersDetailDto {
	
    private int orderDetailsId;      // 前端生成，與 orders 成雙 PK
    private String ordersDetails; // 商品 & 客製化內容
    private int orderDetailsPrice;   // 商品價格 + 客製化
    private int orderId;      // 訂單id
    private int settingId;           // 套餐 ID，單點可為 0 或 -1
    
	public int getOrderDetailsId() {
		return orderDetailsId;
	}
	public void setOrderDetailsId(int orderDetailsId) {
		this.orderDetailsId = orderDetailsId;
	}
	public String getOrdersDetails() {
		return ordersDetails;
	}
	public void setOrdersDetails(String ordersDetails) {
		this.ordersDetails = ordersDetails;
	}
	public int getOrderDetailsPrice() {
		return orderDetailsPrice;
	}
	public void setOrderDetailsPrice(int orderDetailsPrice) {
		this.orderDetailsPrice = orderDetailsPrice;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getSettingId() {
		return settingId;
	}
	public void setSettingId(int settingId) {
		this.settingId = settingId;
	}

}
