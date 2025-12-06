package com.example.GroupProject.request;

import com.example.GroupProject.dto.OrdersDto;
import java.util.List;

public class AddOrdersReq extends OrdersDto {
	
	private List<OrderDetailReq> orderDetailsList; // 明細列表（重要）

	public AddOrdersReq() {
		super();
	}

	public AddOrdersReq(List<OrderDetailReq> orderDetailsList) {
		super();
		this.orderDetailsList = orderDetailsList;
	}

	public List<OrderDetailReq> getOrderDetailsList() {
		return orderDetailsList;
	}

	public void setOrderDetailsList(List<OrderDetailReq> orderDetailsList) {
		this.orderDetailsList = orderDetailsList;
	}
	

}
