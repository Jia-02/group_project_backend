package com.example.GroupProject.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.GroupProject.request.DeliveryTaskReq;
import com.example.GroupProject.request.UpdateStatusReq;
import com.example.GroupProject.response.BasicRes;
import com.example.GroupProject.response.DTaskListRes;
import com.example.GroupProject.service.DeliveryTaskService;



// 外送任務Controller
//@CrossOrigin
@CrossOrigin(origins = "http://192.168.0.174:4200")
@RequestMapping(value = "api/v1/deliverytask/")
@RestController
public class DeliveryTaskController {

	@Autowired
	private DeliveryTaskService service;

	// 撈全部外送任務列表 
	@GetMapping(value = "list/all")
	public DTaskListRes getAllTaskList() {

		return service.getAllTaskList();

	};

	// 店家 撈運送中的任務的列表'pending', 'pickup'
	@GetMapping(value = "list/notend")
	public DTaskListRes getTaskListNotEnd() {

		return service.getTaskListNotEnd();

	};

	// 撈全部外送任務(沒外送員)列表
	@GetMapping(value = "list/available")
	public DTaskListRes getTaskListNoDeliveryId() {

		return service.getTaskListNoDeliveryId();

	};

	// 外送員 接單
	@PostMapping(value = "user/takeorder")
	public BasicRes takeOrder(@RequestBody DeliveryTaskReq req) {

		return service.takeOrder(req.getOrderNo(), req.getDeliveryId());
	}

	// 外送員 撈出進行中的列表
	@GetMapping(value = "list/taking")
	public DTaskListRes getTaskListTaking(@RequestParam("deliveryId") int deliveryId) {

		return service.getTaskListTaking(deliveryId);

	};

	// 撈出該外送員id已完成中的列表(按時間, "日期" )
	@GetMapping(value = "list/complete/date")
	public DTaskListRes getTaskListByDate(@RequestParam("date") LocalDate date,
			@RequestParam("deliveryId") int deliveryId) {

		return service.getTaskListByDate(date, deliveryId);
	}

	// 撈出該外送員id已完成中的列表(按月份或年)
	@GetMapping(value = "list/complete/longtime")
	public DTaskListRes getTaskListByMonthOrYear(@RequestParam("my") int my,
			@RequestParam("deliveryId") int deliveryId,
			@RequestParam("time") String time) {

		return service.getTaskListByMonthOrYear(my, deliveryId, time);
	}
	
	// TODO 外送任務 詳細資料 需要另一組的資料庫資料?
	
	
	
	
	// 改狀態 已取餐 已送達
	@PostMapping(value = "user/statusupdate")
	public BasicRes updateStatus(@RequestBody UpdateStatusReq req) {
		return service.updateStatus(req.getOrderNo(), req.getStatus(), req.getEstimatedTime());
		}

	// 外送員 撈出進行中的列表
	@GetMapping(value = "list/completed")
	public DTaskListRes getAllByDeliveryid(@RequestParam("deliveryId") int deliveryId) {

		return service.getAllByDeliveryid(deliveryId);

	};	
	
	// 更新外送費與距離
	@PostMapping(value = "user/update-money")
	public BasicRes updateDistanceAndMoney(@RequestBody DeliveryTaskReq req) {
	    return service.updateDistanceAndMoney(
	        req.getOrderNo(),
	        req.getDistanceKm(),
	        req.getMoney()
	    );
	}
	
}
