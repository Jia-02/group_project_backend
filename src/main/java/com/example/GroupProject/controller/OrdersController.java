package com.example.GroupProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.GroupProject.request.AddOrdersReq;
import com.example.GroupProject.response.BasicRes;
import com.example.GroupProject.service.OrdersService;

@RestController
@CrossOrigin
public class OrdersController {
	
	@Autowired
	private OrdersService ordersService;

	
	//新增訂單
	@PostMapping(value = "orders/add")
	public BasicRes addOrder(@RequestBody AddOrdersReq req) throws Exception{
		return ordersService.addOrder(req);
	}

}
