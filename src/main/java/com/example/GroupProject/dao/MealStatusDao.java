package com.example.GroupProject.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.GroupProject.dto.MealStatusDto;

@Mapper
public interface MealStatusDao {

	public void addMealStatus(MealStatusDto mealStatus);
	
	public void updateMealStatus(MealStatusDto mealStatus);
	
	public MealStatusDto getMealStatus(@Param("ordersId") int orderId);
	
}
