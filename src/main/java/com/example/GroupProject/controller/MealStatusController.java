package com.example.GroupProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.GroupProject.response.MealStatusRes;
import com.example.GroupProject.service.MealStatusService;

//@CrossOrigin 
@CrossOrigin(origins = "http://192.168.0.174:4200")
@RestController
public class MealStatusController {
	
	@Autowired
	private MealStatusService mealStatusService;
	
	@GetMapping(value = "meal/status")
	public MealStatusRes getMealStatus(@RequestParam("orderId") int orderId) throws Exception {
		return mealStatusService.getMealStatus(orderId);
	}
	
}
