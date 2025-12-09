package com.example.GroupProject.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.GroupProject.request.AddOrdersReq;
import com.example.GroupProject.request.OrderUpdateReq;
import com.example.GroupProject.response.BasicRes;
import com.example.GroupProject.response.OrdersAllDetailRes;
import com.example.GroupProject.response.OrdersListRes;
import com.example.GroupProject.response.OrdersMealListRes;
import com.example.GroupProject.response.OrdersMealRes;
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
	
	//更新訂單(初始狀態尚未付款)
	@PostMapping(value = "orders/update/nopaid")
	public BasicRes updateOrderInNoPaid(@RequestBody AddOrdersReq req) throws Exception{
		return ordersService.updateOrderInNoPaid(req);
	}
	
	//更新訂單(已付款改餐點狀態)
	@PostMapping(value = "orders/update/ispaid")
	public BasicRes updateOrderIsPaid(@RequestBody  OrderUpdateReq req) throws Exception{
		return ordersService.updateOrderIsPaid(req);
	}
	
	//查詢訂單列表(訂單管理者用)
	@GetMapping(value = "orders/list")
	public OrdersListRes getOrdersList() {
		return ordersService.getOrdersList();
	}
	
	//透過ordersId查詢單筆訂單資訊與細節
	@GetMapping(value = "orders/list/detail")
	public OrdersAllDetailRes getOrdersAllDetailById(@RequestParam("ordersId") int ordersId) throws Exception {
		return ordersService.getOrdersAllDetailById(ordersId);
	}
	
	//透過ordersId查詢單筆訂單資訊與細節(有工作台id版本)
	@GetMapping(value = "orders/meal")
	public OrdersMealRes getOrdersMealById(@RequestParam("ordersId") int ordersId) throws Exception {
		return ordersService.getOrdersMealById(ordersId);
	}
	
	//透過ordersId查詢單筆訂單資訊與細節(有工作台id版本)
	@GetMapping(value = "orders/meal/list")
	public OrdersMealListRes getOrdersMealByDate(@RequestParam("ordersDate") LocalDate ordersDate) throws Exception {
		return ordersService.getOrdersMealByDate(ordersDate);
	}
}
