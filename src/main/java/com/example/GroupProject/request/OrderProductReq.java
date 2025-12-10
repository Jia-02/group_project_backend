package com.example.GroupProject.request;

import java.util.List;

import com.example.GroupProject.dto.OptionDetailDto;

public class OrderProductReq {

    private int categoryId;
    private int productId;
    private String productName;
    private int productPrice;
    private String mealStatus;

    private List<OptionDetailDto> detailList;  // 客製化

	public OrderProductReq() {
		super();
	}

	public OrderProductReq(int categoryId, int productId, String productName, int productPrice, String mealStatus,
			List<OptionDetailDto> detailList) {
		super();
		this.categoryId = categoryId;
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
