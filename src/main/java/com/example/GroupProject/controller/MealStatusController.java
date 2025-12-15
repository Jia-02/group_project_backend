package com.example.GroupProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.GroupProject.response.MealStatusRes;
import com.example.GroupProject.service.MealStatusService;

@CrossOrigin // 允許前端來源
@RestController
public class MealStatusController {
	
	@Autowired
	private MealStatusService mealStatusService;
	
	@GetMapping(value = "meal/status")
	public MealStatusRes getMealStatus(@RequestParam("orderId") int orderId) throws Exception {
		return mealStatusService.getMealStatus(orderId);
	}
	
}
