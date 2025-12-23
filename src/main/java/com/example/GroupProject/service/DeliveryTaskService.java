package com.example.GroupProject.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.example.GroupProject.constants.ResCodeMessage;
import com.example.GroupProject.dao.DeliveryTaskDao;
import com.example.GroupProject.dao.MealStatusDao;
import com.example.GroupProject.dto.DeliveryTask;
import com.example.GroupProject.dto.MealStatusDto;
import com.example.GroupProject.response.BasicRes;
import com.example.GroupProject.response.DTaskListRes;



@Service
public class DeliveryTaskService {

	@Autowired
	private DeliveryTaskDao dao;

	@Autowired
	private MealStatusDao mealstatusdao;

	// 撈全部外送任務列表
	public DTaskListRes getAllTaskList() {

		List<DeliveryTask> dtList = new ArrayList<>();

		try {

			dtList = dao.getAll();

			if (dtList == null) {
				return new DTaskListRes(ResCodeMessage.DTASK_NOT_FOUND.getCode(), //
						ResCodeMessage.DTASK_NOT_FOUND.getMessage());
			}

		} catch (Exception e) {
			throw e;
		}

		return new DTaskListRes(ResCodeMessage.SUCCESS.getCode(), //
				ResCodeMessage.SUCCESS.getMessage(), dtList);
	}

	// 撈運送中的任務的列表
	public DTaskListRes getTaskListNotEnd() {

		List<DeliveryTask> dtList = new ArrayList<>();

		try {

			dtList = dao.getAllNotEnd();

			if (dtList == null) {
				return new DTaskListRes(ResCodeMessage.DTASK_NOT_FOUND.getCode(), //
						ResCodeMessage.DTASK_NOT_FOUND.getMessage());
			}

		} catch (Exception e) {
			throw e;
		}

		return new DTaskListRes(ResCodeMessage.SUCCESS.getCode(), //
				ResCodeMessage.SUCCESS.getMessage(), dtList);
	}

	// 撈全部外送任務(沒外送員)列表
	public DTaskListRes getTaskListNoDeliveryId() {

		List<DeliveryTask> dtList = new ArrayList<>();

		try {

			dtList = dao.getAllNoDeliveryId();

			if (dtList == null) {
				return new DTaskListRes(ResCodeMessage.DTASK_NOT_FOUND.getCode(), //
						ResCodeMessage.DTASK_NOT_FOUND.getMessage());
			}

		} catch (Exception e) {
			throw e;
		}

		return new DTaskListRes(ResCodeMessage.SUCCESS.getCode(), //
				ResCodeMessage.SUCCESS.getMessage(), dtList);
	};

	// 外送員 接單
	public BasicRes takeOrder(String orderNo, int deliveryId) {

		// 檢查參數
		if (!StringUtils.hasText(orderNo)) {
			return new BasicRes(ResCodeMessage.PARAM_ORDERNO_ERROR.getCode(), //
					ResCodeMessage.PARAM_ORDERNO_ERROR.getMessage());
		}

		if (deliveryId < 0) {
			return new DTaskListRes(ResCodeMessage.PARAM_DELIVERYID_ERROR.getCode(), //
					ResCodeMessage.PARAM_DELIVERYID_ERROR.getMessage());
		}

		try {
			// 檢查是否有這名外送員ID
			int count = dao.getDeliveryUserCountById(deliveryId);
			if (count == 0) {
				return new BasicRes(ResCodeMessage.DELIVERY_USER_NOT_FOUND.getCode(), //
						ResCodeMessage.DELIVERY_USER_NOT_FOUND.getMessage());
			}
			// 檢查是否有這筆訂單
			count = dao.getCountByOrderNo(orderNo);
			if (count == 0) {
				return new BasicRes(ResCodeMessage.DTASK_NOT_FOUND.getCode(), //
						ResCodeMessage.DTASK_NOT_FOUND.getMessage());
			}

			// 將ID填入外送任務中
			dao.updateDeliveryIdToDTask(orderNo, deliveryId);

		} catch (Exception e) {
			throw e;
		}

		return new BasicRes(ResCodeMessage.SUCCESS.getCode(), //
				ResCodeMessage.SUCCESS.getMessage());

	}

	// 外送員 撈出進行中的列表
	public DTaskListRes getTaskListTaking(int deliveryId) {

		// 檢查參數
		if (deliveryId < 0) {
			return new DTaskListRes(ResCodeMessage.PARAM_DELIVERYID_ERROR.getCode(), //
					ResCodeMessage.PARAM_DELIVERYID_ERROR.getMessage());
		}

		List<DeliveryTask> dtList = new ArrayList<>();
		try {
			// 檢查是否有這名外送員ID
			int count = dao.getDeliveryUserCountById(deliveryId);
			if (count == 0) {
				return new DTaskListRes(ResCodeMessage.DELIVERY_USER_NOT_FOUND.getCode(), //
						ResCodeMessage.DELIVERY_USER_NOT_FOUND.getMessage());
			}

			// 撈出進行中的列表
			dtList = dao.getTaskListTaking(deliveryId);

			if (dtList == null) {
				return new DTaskListRes(ResCodeMessage.DTASK_NOT_FOUND.getCode(), //
						ResCodeMessage.DTASK_NOT_FOUND.getMessage());
			}

		} catch (Exception e) {
			throw e;
		}

		return new DTaskListRes(ResCodeMessage.SUCCESS.getCode(), //
				ResCodeMessage.SUCCESS.getMessage(), dtList);
	}

	// 撈出該外送員id已完成中的列表(按日期)
	public DTaskListRes getTaskListByDate(LocalDate date, int deliveryId) {

		// 判斷參數
		if (deliveryId < 0) {
			return new DTaskListRes(ResCodeMessage.PARAM_DELIVERYID_ERROR.getCode(), //
					ResCodeMessage.PARAM_DELIVERYID_ERROR.getMessage());
		}

		if (date == null) {
			return new DTaskListRes(ResCodeMessage.PARAM_DATE_ERROR.getCode(), //
					ResCodeMessage.PARAM_DATE_ERROR.getMessage());
		}

		List<DeliveryTask> dtList = new ArrayList<>();
		try {
			// 檢查是否有這名外送員ID
			int count = dao.getDeliveryUserCountById(deliveryId);
			if (count == 0) {
				return new DTaskListRes(ResCodeMessage.DELIVERY_USER_NOT_FOUND.getCode(), //
						ResCodeMessage.DELIVERY_USER_NOT_FOUND.getMessage());
			}

			// 撈當天訂單列表
			dtList = dao.getTaskListByDate(date, deliveryId);

			if (dtList == null) {
				return new DTaskListRes(ResCodeMessage.DTASK_NOT_FOUND.getCode(), //
						ResCodeMessage.DTASK_NOT_FOUND.getMessage());
			}

		} catch (Exception e) {
			throw e;
		}

		return new DTaskListRes(ResCodeMessage.SUCCESS.getCode(), //
				ResCodeMessage.SUCCESS.getMessage(), dtList);
	}

	// 撈出該外送員id已完成中的列表(按月)
	public DTaskListRes getTaskListByMonthOrYear(int my, int deliveryId, String time) {

		// 判斷參數
		if (my < 0) {
			return new DTaskListRes(ResCodeMessage.PARAM_MY_ERROR.getCode(), //
					ResCodeMessage.PARAM_MY_ERROR.getMessage());
		}

		if (deliveryId < 0) {
			return new DTaskListRes(ResCodeMessage.PARAM_DELIVERYID_ERROR.getCode(), //
					ResCodeMessage.PARAM_DELIVERYID_ERROR.getMessage());
		}

		// time判斷
		// 1. time不能為null
		// 2. 根據 time 去決定 my 的範圍
		// 3. time只能是 "month" 或 "year"
		if (!StringUtils.hasText(time)) {
			return new DTaskListRes(ResCodeMessage.PARAM_TIME_ERROR.getCode(), //
					ResCodeMessage.PARAM_TIME_ERROR.getMessage());
		}

		if (time.equalsIgnoreCase("month")) {
			if (my > 12) {
				return new DTaskListRes(ResCodeMessage.PARAM_MONTH_ERROR.getCode(), //
						ResCodeMessage.PARAM_MONTH_ERROR.getMessage());
			}
		} else if (time.equalsIgnoreCase("year")) {

		} else {
			return new DTaskListRes(ResCodeMessage.PARAM_TIME_ERROR.getCode(), //
					ResCodeMessage.PARAM_TIME_ERROR.getMessage());
		}

		List<DeliveryTask> dtList = new ArrayList<>();
		try {
			// 檢查是否有這名外送員ID
			int count = dao.getDeliveryUserCountById(deliveryId);
			if (count == 0) {
				return new DTaskListRes(ResCodeMessage.DELIVERY_USER_NOT_FOUND.getCode(), //
						ResCodeMessage.DELIVERY_USER_NOT_FOUND.getMessage());
			}

			// 檢查時間time是 month 還是 year，並呼叫對應的dao

			if (time.equalsIgnoreCase("month")) {
				// 撈當月訂單列表
				dtList = dao.getTaskListByMonth(my, deliveryId);

			} else if (time.equalsIgnoreCase("year")) {
				// 撈當年訂單列表
				dtList = dao.getTaskListByYear(my, deliveryId);

			}

			if (dtList == null) {
				return new DTaskListRes(ResCodeMessage.DTASK_NOT_FOUND.getCode(), //
						ResCodeMessage.DTASK_NOT_FOUND.getMessage());
			}

		} catch (Exception e) {
			throw e;
		}

		return new DTaskListRes(ResCodeMessage.SUCCESS.getCode(), //
				ResCodeMessage.SUCCESS.getMessage(), dtList);
	}

	// TODO

	// 改狀態 已取餐 已送達 順邊更新另外一組的狀態
	@Transactional
	public BasicRes updateStatus(String orderNo, String status, Integer estimatedTime) {

		// 檢查參數
		if (!StringUtils.hasText(orderNo)) {
			return new BasicRes(ResCodeMessage.PARAM_ORDERNO_ERROR.getCode(), //
					ResCodeMessage.PARAM_ORDERNO_ERROR.getMessage());
		}

		if (!StringUtils.hasText(status)) {
			return new BasicRes(ResCodeMessage.PARAM_STATUS_ERROR.getCode(), //
					ResCodeMessage.PARAM_STATUS_ERROR.getMessage());
		}

		if (!status.equalsIgnoreCase("completed") && !status.equalsIgnoreCase("pickup")) {
			return new BasicRes(ResCodeMessage.PARAM_STATUS_ERROR.getCode(), //
					ResCodeMessage.PARAM_STATUS_ERROR.getMessage());
		}

		try {
			// 檢查是否有這筆訂單
			int count = dao.getCountByOrderNo(orderNo);
			if (count == 0) {
				return new BasicRes(ResCodeMessage.DTASK_NOT_FOUND.getCode(), //
						ResCodeMessage.DTASK_NOT_FOUND.getMessage());
			}
			// 更新狀態
			dao.updateStatus(orderNo, status);
			// 3️⃣ 用 order_no 找 orders_id
			Integer ordersId = dao.findOrdersIdByOrderNo(orderNo);
			if (ordersId == null) {
				throw new RuntimeException("找不到對應的 orders_id，order_no=" + orderNo);
			}

			MealStatusDto mealStatusDto = new MealStatusDto();
			mealStatusDto.setOrdersId(ordersId);

			 // 「取餐」檢查內場
		    if ("pickup".equalsIgnoreCase(status)) {

		        String mealStatus = mealstatusdao.getMealStatusByOrdersId(ordersId);

		        //  內場還沒完成
		        if (!"外送員已取餐".equals(mealStatus)) {
		            return new BasicRes(
		            		ResCodeMessage.MEAL_NOT_COMPLETED.getCode(), //
							ResCodeMessage.MEAL_NOT_COMPLETED.getMessage()
		            );
		        }
		    }

			
			if ("pickup".equalsIgnoreCase(status)) {
				mealStatusDto.setMealStatus("外送員已取餐");
				mealStatusDto.setEstimatedTime(estimatedTime); // 預計送達時間
				mealStatusDto.setFinishTime(LocalTime.now());
			}

			if ("completed".equalsIgnoreCase(status)) {
				mealStatusDto.setMealStatus("已送達");
				mealStatusDto.setEstimatedTime(0);
				mealStatusDto.setFinishTime(LocalTime.now());
			}

			mealstatusdao.updateMealStatus(mealStatusDto);

		} catch (Exception e) {

			throw e;
		}

		return new BasicRes(ResCodeMessage.SUCCESS.getCode(), ResCodeMessage.SUCCESS.getMessage());
	}

	// 外送員 撈出進行中的列表
	public DTaskListRes getAllByDeliveryid(int deliveryId) {
		if (deliveryId < 0) {
			return new DTaskListRes(ResCodeMessage.PARAM_DELIVERYID_ERROR.getCode(), //
					ResCodeMessage.PARAM_DELIVERYID_ERROR.getMessage());
		}

		List<DeliveryTask> dtList = new ArrayList<>();
		try {
			// 檢查是否有這名外送員ID
			int count = dao.getDeliveryUserCountById(deliveryId);
			if (count == 0) {
				return new DTaskListRes(ResCodeMessage.DELIVERY_USER_NOT_FOUND.getCode(), //
						ResCodeMessage.DELIVERY_USER_NOT_FOUND.getMessage());
			}

			// 撈出進行中的列表
			dtList = dao.getAllByDeliveryid(deliveryId);

			if (dtList == null) {
				return new DTaskListRes(ResCodeMessage.DTASK_NOT_FOUND.getCode(), //
						ResCodeMessage.DTASK_NOT_FOUND.getMessage());
			}

		} catch (Exception e) {
			throw e;
		}

		return new DTaskListRes(ResCodeMessage.SUCCESS.getCode(), //
				ResCodeMessage.SUCCESS.getMessage(), dtList);
	}

	// 更新外送費和錢
	public BasicRes updateDistanceAndMoney(String orderNo, BigDecimal distanceKm, BigDecimal money) {
		if (!StringUtils.hasText(orderNo)) {
			return new BasicRes(ResCodeMessage.PARAM_ORDERNO_ERROR.getCode(),
					ResCodeMessage.PARAM_ORDERNO_ERROR.getMessage());
		}

		if (distanceKm.compareTo(BigDecimal.ZERO) <= 0 || money.compareTo(BigDecimal.ZERO) < 0) {
			return new BasicRes(ResCodeMessage.KM_MONEY_ERROR.getCode(), ResCodeMessage.KM_MONEY_ERROR.getMessage());
		}

		try {
			int count = dao.getCountByOrderNo(orderNo);
			if (count == 0) {
				return new BasicRes(ResCodeMessage.DTASK_NOT_FOUND.getCode(),
						ResCodeMessage.DTASK_NOT_FOUND.getMessage());
			}

			DeliveryTask task = dao.getTaskByOrderNo(orderNo);
			if (task.getMoney() != null && task.getMoney().compareTo(BigDecimal.ZERO) > 0) {
				return new BasicRes(ResCodeMessage.MONEY_ALREADY_EXISTS.getCode(),
						ResCodeMessage.MONEY_ALREADY_EXISTS.getMessage());
			}

			dao.updateDistanceAndMoney(orderNo, distanceKm, money);

		} catch (Exception e) {
			throw e;
		}

		return new BasicRes(ResCodeMessage.SUCCESS.getCode(), ResCodeMessage.SUCCESS.getMessage());
	}

}
