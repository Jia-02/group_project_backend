package com.example.GroupProject.response;

import java.util.List;

import com.example.GroupProject.vo.ProductVo;

public class ProductRes extends BasicRes {

	private int categoryId;
	private List<ProductVo> productList;

	public ProductRes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductRes(int code, String message) {
		super(code, message);
		// TODO Auto-generated constructor stub
	}

	public ProductRes(int code, String message, int categoryId, List<ProductVo> productList) {
		super(code, message);
		this.categoryId = categoryId;
		this.productList = productList;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public List<ProductVo> getProductList() {
		return productList;
	}

	public void setProductList(List<ProductVo> productList) {
		this.productList = productList;
	}

}
