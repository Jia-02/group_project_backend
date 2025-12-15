package com.example.GroupProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.GroupProject.constants.ResCodeMessage;
import com.example.GroupProject.dao.MealStatusDao;
import com.example.GroupProject.dao.OrdersDao;
import com.example.GroupProject.response.MealStatusRes;

@Service
public class MealStatusService {

	@Autowired
	private MealStatusDao mealStatusDao;

	@Autowired
	private OrdersDao ordersDao;

	@Transactional(rollbackFor = Exception.class)
	public MealStatusRes getMealStatus(int orderId) {

		if (orderId < 1) {
			return new MealStatusRes(ResCodeMessage.ORDERS_ID_ERROR.getCode(), //
					ResCodeMessage.ORDERS_ID_ERROR.getMessage());
		}

		return new MealStatusRes(ResCodeMessage.SUCCESS.getCode(), //
				ResCodeMessage.SUCCESS.getMessage(), mealStatusDao.getMealStatus(orderId), //
				ordersDao.getOrdersById(orderId));

	}

}
