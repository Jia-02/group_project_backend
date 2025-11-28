package com.example.GroupProject.response;

import java.util.List;

import com.example.GroupProject.dto.ProductDto;


public class ProductRes extends BasicRes {

	private List<ProductDto> productList;

	public ProductRes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductRes(int code, String message) {
		super(code, message);
		// TODO Auto-generated constructor stub
	}

	public ProductRes(int code, String message, List<ProductDto> productList) {
		super(code, message);
		this.productList = productList;
	}

	public List<ProductDto> getProductList() {
		return productList;
	}

	public void setProductList(List<ProductDto> productList) {
		this.productList = productList;
	}

}
