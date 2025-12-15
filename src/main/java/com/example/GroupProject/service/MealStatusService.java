package com.example.GroupProject.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.example.GroupProject.constants.ResCodeMessage;
import com.example.GroupProject.dao.MealStatusDao;
import com.example.GroupProject.dao.OrdersDao;
import com.example.GroupProject.dto.OrderDetailDto;
import com.example.GroupProject.dto.OrdersDto;
import com.example.GroupProject.request.OrderDetailReq;
import com.example.GroupProject.request.OrderProductReq;
import com.example.GroupProject.response.MealStatusRes;
import com.example.GroupProject.response.OrdersAllDetailRes;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MealStatusService {

	@Autowired
	private MealStatusDao mealStatusDao;

	@Autowired
	private OrdersDao ordersDao;

	private ObjectMapper mapper = new ObjectMapper();

	@Transactional(rollbackFor = Exception.class)
	public MealStatusRes getMealStatus(int ordersId) throws Exception {

		if (ordersId < 1) {
			return new MealStatusRes(ResCodeMessage.ORDERS_ID_ERROR.getCode(), //
					ResCodeMessage.ORDERS_ID_ERROR.getMessage());
		}

		OrdersDto dto = ordersDao.getOrdersById(ordersId);
		if (dto == null) {
			return new MealStatusRes(ResCodeMessage.ORDERS_NOT_FOUND.getCode(),
					ResCodeMessage.ORDERS_NOT_FOUND.getMessage());
		}

		// 取得明細列表
		List<OrderDetailDto> dbDetailList = ordersDao.getOrderDetailById(ordersId);

		// 不可為null
		if (dbDetailList == null || dbDetailList.isEmpty()) {
			return new MealStatusRes(ResCodeMessage.ORDER_DETAIL_EMPTY.getCode(),
					ResCodeMessage.ORDER_DETAIL_EMPTY.getMessage());
		}

		// 晚點存入所有訂單細節
		List<OrderDetailReq> finalList = new ArrayList<>();

		// 處理每一筆 order_details ---
		for (OrderDetailDto detail : dbDetailList) {

			// 建立單一細節做存放
			OrderDetailReq detailReq = new OrderDetailReq();
			detailReq.setOrderDetailsId(detail.getOrderDetailsId());
			detailReq.setOrderDetailsPrice(detail.getOrderDetailsPrice());
			detailReq.setSettingId(detail.getSettingId());

			// order_details (字串 → List<OrderProductReq>)
			// 存放OrderProductReq列表
			String jsonString = detail.getOrderDetails();
			List<OrderProductReq> productList = new ArrayList<>();
			if (StringUtils.hasText(jsonString)) {
				try {
					productList = mapper.readValue(jsonString, new TypeReference<List<OrderProductReq>>() {
					});
				} catch (Exception e) {
					throw e;
				}
			}

			detailReq.setOrderDetails(productList);

			// 加入最終列表
			finalList.add(detailReq);
		}

		return new MealStatusRes(ResCodeMessage.SUCCESS.getCode(), //
				ResCodeMessage.SUCCESS.getMessage(), mealStatusDao.getMealStatus(ordersId), //
				ordersDao.getOrdersById(ordersId), finalList);

	}

}
