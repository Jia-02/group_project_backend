package com.example.GroupProject.vo;

import java.util.List;


public class OrdersMealVo {
	
    private int orderDetailsId;       // 前端生成
    private int orderDetailsPrice;    // 單點＋客製化總價
    private int settingId;            // 套餐 ID，可為 0 或 -1

    private List<OrderMealDetailVo> orderDetails; // 一筆明細內可能有多個商品

	public OrdersMealVo() {
		super();
	}

	public OrdersMealVo(int orderDetailsId, int orderDetailsPrice, int settingId,
			List<OrderMealDetailVo> orderDetails) {
		super();
		this.orderDetailsId = orderDetailsId;
		this.orderDetailsPrice = orderDetailsPrice;
		this.settingId = settingId;
		this.orderDetails = orderDetails;
	}

	public int getOrderDetailsId() {
		return orderDetailsId;
	}

	public void setOrderDetailsId(int orderDetailsId) {
		this.orderDetailsId = orderDetailsId;
	}

	public int getOrderDetailsPrice() {
		return orderDetailsPrice;
	}

	public void setOrderDetailsPrice(int orderDetailsPrice) {
		this.orderDetailsPrice = orderDetailsPrice;
	}

	public int getSettingId() {
		return settingId;
	}

	public void setSettingId(int settingId) {
		this.settingId = settingId;
	}

	public List<OrderMealDetailVo> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderMealDetailVo> orderDetails) {
		this.orderDetails = orderDetails;
	}

}
