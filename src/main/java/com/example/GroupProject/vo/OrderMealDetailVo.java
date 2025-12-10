package com.example.GroupProject.vo;

import java.util.List;

import com.example.GroupProject.dto.OptionDetailDto;

public class OrderMealDetailVo {
	
    private int categoryId;
    private int workStationId;
    private int productId;
    private String productName;
    private int productPrice;
    private String mealStatus;

    private List<OptionDetailDto> detailList;  // 客製化

	public OrderMealDetailVo() {
		super();
	}

	public OrderMealDetailVo(int categoryId, int workStationId, int productId, String productName, int productPrice,
			String mealStatus, List<OptionDetailDto> detailList) {
		super();
		this.categoryId = categoryId;
		this.workStationId = workStationId;
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.mealStatus = mealStatus;
		this.detailList = detailList;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getWorkStationId() {
		return workStationId;
	}

	public void setWorkStationId(int workStationId) {
		this.workStationId = workStationId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public String getMealStatus() {
		return mealStatus;
	}

	public void setMealStatus(String mealStatus) {
		this.mealStatus = mealStatus;
	}

	public List<OptionDetailDto> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<OptionDetailDto> detailList) {
		this.detailList = detailList;
	}

}
