package com.example.GroupProject.response;

import java.util.List;

import com.example.GroupProject.vo.OrdersMealListVo;

public class OrdersMealListRes extends BasicRes {
	
	 private List<OrdersMealListVo> orders;

	 public OrdersMealListRes() {
		super();
	 }

	 public OrdersMealListRes(int code, String message) {
		super(code, message);
	 }

	 public OrdersMealListRes(int code, String message, List<OrdersMealListVo> orders) {
		super(code, message);
		this.orders = orders;
	 }

	 public List<OrdersMealListVo> getOrders() {
		 return orders;
	 }

	 public void setOrders(List<OrdersMealListVo> orders) {
		 this.orders = orders;
	 }

}
