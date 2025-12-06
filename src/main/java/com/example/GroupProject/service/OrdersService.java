package com.example.GroupProject.service;

import java.util.HashSet;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.example.GroupProject.constants.ResCodeMessage;
import com.example.GroupProject.dao.CategoryDao;
import com.example.GroupProject.dao.OrdersDao;
import com.example.GroupProject.dao.ProductDao;
import com.example.GroupProject.dao.SettingDao;
import com.example.GroupProject.dao.TablesDao;
import com.example.GroupProject.dto.OptionDetailDto;
import com.example.GroupProject.dto.OrderDetailDto;
import com.example.GroupProject.dto.OrdersDto;
import com.example.GroupProject.dto.ProductDto;
import com.example.GroupProject.request.AddOrdersReq;
import com.example.GroupProject.request.OrderDetailReq;
import com.example.GroupProject.request.OrderProductReq;
import com.example.GroupProject.response.BasicRes;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OrdersService {

	// json跟java物件的轉換
	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private OrdersDao ordersDao;

	@Autowired
	private TablesDao tableDao;

	@Autowired
	private SettingDao settingDao;

	@Autowired
	private ProductDao productDao;

	@Autowired
	private CategoryDao categoryDao;

	// 私有方法轉換日期
	private String generateOrderCode(int orderId, String type, String orderDate, String orderTime) {
		// 取年份後兩位
		String yy = orderDate.substring(2, 4); // "2025" -> "25"
		String MMdd = orderDate.substring(5, 7) + orderDate.substring(8, 10); // "12-05" -> "1205"
		// 取時間前兩個小時 + 分鐘
		String HHmm = orderTime.substring(0, 2) + orderTime.substring(3, 5); // "12:00:00" -> "1200"
		// 流水號補零
		String serial = String.format("%02d", orderId);
		String finalCode = yy + MMdd + HHmm + type + serial;
		return finalCode;
	}

	// 新增訂單
	@Transactional(rollbackFor = Exception.class)
	public BasicRes addOrder(AddOrdersReq req) throws Exception {

		// ================= 1. 基礎資訊判斷 =================
		// 判斷類型不可為空
		if (!StringUtils.hasText(req.getOrdersType())) {
			return new BasicRes(ResCodeMessage.ORDERS_TYPE_ERROR.getCode(), //
					ResCodeMessage.ORDERS_TYPE_ERROR.getMessage());
		}

		// 判斷日期不可為空
		if (req.getOrdersDate() == null) {
			return new BasicRes(ResCodeMessage.ORDERS_DATE_ERROR.getCode(), //
					ResCodeMessage.ORDERS_DATE_ERROR.getMessage());
		}

		// 判斷時間不可為空
		if (req.getOrdersTime() == null) {
			return new BasicRes(ResCodeMessage.ORDERS_TIME_ERROR.getCode(), //
					ResCodeMessage.ORDERS_TIME_ERROR.getMessage());
		}

		// 判斷總價格不可 <= 0
		if (req.getTotalPrice() <= 0) {
			return new BasicRes(ResCodeMessage.ORDERS_TOTAL_PRICE_ERROR.getCode(), //
					ResCodeMessage.ORDERS_TOTAL_PRICE_ERROR.getMessage());
		}

		// 判斷付款類型不可為空
		if (!StringUtils.hasText(req.getPaymentType())) {
			return new BasicRes(ResCodeMessage.PAYMENT_TYPE_ERROR.getCode(), //
					ResCodeMessage.PAYMENT_TYPE_ERROR.getMessage());
		}

		// 內用需要桌號
		if ("A".equals(req.getOrdersType())) {
			// 桌號不可為空
			if (!StringUtils.hasText(req.getTableId())) {
				return new BasicRes(ResCodeMessage.TABLE_NOT_FOUND.getCode(),
						ResCodeMessage.TABLE_NOT_FOUND.getMessage());
			}

			// 檢查桌位是否存在
			if (!tableDao.existsById(req.getTableId())) {
				return new BasicRes(ResCodeMessage.TABLE_NOT_FOUND.getCode(),
						ResCodeMessage.TABLE_NOT_FOUND.getMessage());
			}
		}

		// 外帶必須有顧客資訊
		if ("T".equals(req.getOrdersType()) && (!StringUtils.hasText(req.getCustomerName()) //
				|| !StringUtils.hasText(req.getCustomerPhone()))) {
			return new BasicRes(ResCodeMessage.CUSTOMER_INFO_ERROR.getCode(), //
					ResCodeMessage.CUSTOMER_INFO_ERROR.getMessage());
		}

		// 外送要有地址 + 顧客資訊
		if ("D".equals(req.getOrdersType()) && (!StringUtils.hasText(req.getCustomerName()) //
				|| !StringUtils.hasText(req.getCustomerPhone()) || //
				!StringUtils.hasText(req.getCustomerAddress()))) {
			return new BasicRes(ResCodeMessage.CUSTOMER_INFO_ERROR.getCode(), //
					ResCodeMessage.CUSTOMER_INFO_ERROR.getMessage());
		}

		// ================= 2. 訂單明細判斷 =================
		// 取得訂單細節
		List<OrderDetailReq> detailsList = req.getOrderDetailsList();
		if (detailsList == null || detailsList.isEmpty()) {
			return new BasicRes(ResCodeMessage.ORDER_DETAIL_EMPTY.getCode(),
					ResCodeMessage.ORDER_DETAIL_EMPTY.getMessage());
		}

		// detailIds不可重複
		Set<Integer> detailIds = new HashSet<>();
		// 稍晚計算總額 = 細節金額加總
		int calcTotalPrice = 0;
		for (OrderDetailReq detail : detailsList) {

			// orderDetailsId 必須大於0且不重複
			if (detail.getOrderDetailsId() <= 0 || !detailIds.add(detail.getOrderDetailsId())) {
				return new BasicRes(ResCodeMessage.ORDER_DETAIL_ID_ERROR.getCode(),
						ResCodeMessage.ORDER_DETAIL_ID_ERROR.getMessage());
			}

			// 細節金額不可 <= 0
			if (detail.getOrderDetailsPrice() <= 0) {
				return new BasicRes(ResCodeMessage.ORDER_DETAIL_PRICE_ERROR.getCode(),
						ResCodeMessage.ORDER_DETAIL_PRICE_ERROR.getMessage());
			}

			// 判斷如果 settingId >0 套餐是否存在
			int settingId = detail.getSettingId();
			if (settingId > 0) {
				if (settingDao.checkSettingExist(settingId) == 0) {
					return new BasicRes(ResCodeMessage.SETTING_NOT_FOUND.getCode(),
							ResCodeMessage.SETTING_NOT_FOUND.getMessage());
				}
			} else if (settingId < -1) {
				return new BasicRes(ResCodeMessage.SETTING_ID_ERROR.getCode(),
						ResCodeMessage.SETTING_ID_ERROR.getMessage());
			}

			// ---------------- 客人選擇的商品+客製化細節 ----------------
			List<OrderProductReq> products = detail.getOrderDetails();

			// 商品細節不可為空
			if (products == null || products.isEmpty()) {
				return new BasicRes(ResCodeMessage.DETAIL_PRODUCT_LIST_EMPTY.getCode(),
						ResCodeMessage.DETAIL_PRODUCT_LIST_EMPTY.getMessage());
			}

			for (OrderProductReq product : products) {
				int productId = product.getProductId();
				int categoryId = product.getCategoryId();

				// 分類id不可小0，null
				if (categoryId <= 0) {
					return new BasicRes(ResCodeMessage.CATEGORY_ID_ERROR.getCode(), //
							ResCodeMessage.CATEGORY_ID_ERROR.getMessage());
				}

				// 分類存在與否
				if (categoryDao.checkCategoryExistById(categoryId) == 0) {
					return new BasicRes(//
							ResCodeMessage.CATEGORY_IS_NOT_FOUND.getCode(), //
							ResCodeMessage.CATEGORY_IS_NOT_FOUND.getMessage());
				}

				// 餐點狀態不可為空
				if (!StringUtils.hasText(product.getMealStatus())) {
					return new BasicRes(ResCodeMessage.MEAL_STATUS_EMPTY.getCode(), //
							ResCodeMessage.MEAL_STATUS_EMPTY.getMessage());
				}

				// 餐點狀態是否存在(待補上)

				// 透過商品id呼叫商品資訊
				ProductDto productDto = productDao.getDetailByProductId(categoryId, productId);

				// 商品名稱不可為空或是否對應
				if (!StringUtils.hasText(product.getProductName()) //
						|| !productDto.getProductName().equals(product.getProductName())) {
					return new BasicRes(ResCodeMessage.PRODUCT_NAME_ERROR.getCode(), //
							ResCodeMessage.PRODUCT_NAME_ERROR.getMessage());
				}

				// 商品價格不可<0，商品價格是否對應
				if (product.getProductPrice() <= 0 || //
						productDto.getProductPrice() != (product.getProductPrice())) {
					return new BasicRes(ResCodeMessage.PRODUCT_PRICE_ERROR.getCode(),
							ResCodeMessage.PRODUCT_PRICE_ERROR.getMessage());
				}

				// 商品id不可 <= 0
				if (productId <= 0) {
					return new BasicRes(ResCodeMessage.PRODUCT_ID_ERROR.getCode(),
							ResCodeMessage.PRODUCT_ID_ERROR.getMessage());
				}

				// 檢查商品是否存在
				if (productDao.checkProductExist(categoryId, productId) == 0) {
					return new BasicRes(ResCodeMessage.PRODUCT_NOT_FOUND.getCode(),
							ResCodeMessage.PRODUCT_NOT_FOUND.getMessage());
				}

				// 商品細節的 categoryId 與 detailCategoryId 匹配
				if (productDto.getCategoryId() != categoryId) {
					return new BasicRes(//
							ResCodeMessage.PRODUCT_AND_CATEGORY_NOT_MATCH.getCode(), //
							ResCodeMessage.PRODUCT_AND_CATEGORY_NOT_MATCH.getMessage());
				}

				List<OptionDetailDto> options = product.getDetailList();
				// 客製化細節不可為空
				if (options == null) {
					return new BasicRes(ResCodeMessage.OPTION_EMPTY.getCode(),
							ResCodeMessage.OPTION_EMPTY.getMessage());
				}

				for (OptionDetailDto option : options) {
					// 客製化選項名稱不可為空
					if (!StringUtils.hasText(option.getOption())) {
						return new BasicRes(ResCodeMessage.OPTION_DETAIL_NAME_EMPTY.getCode(),
								ResCodeMessage.OPTION_DETAIL_NAME_EMPTY.getMessage());
					}
					// 客製化選項價格不可 < 0
					if (option.getAddPrice() < 0) {
						return new BasicRes(ResCodeMessage.OPTION_DETAIL_PRICE_INVALID.getCode(),
								ResCodeMessage.OPTION_DETAIL_PRICE_INVALID.getMessage());
					}
				}
			}
			// 計算單筆商品價格(套餐+客製化 or 餐點+客製化價格 -前端給)
			calcTotalPrice += detail.getOrderDetailsPrice();
		}

		// 驗證前端 totalPrice 是否正確
		if (calcTotalPrice != req.getTotalPrice()) {
			return new BasicRes(ResCodeMessage.TOTAL_PRICE_MISMATCH.getCode(),
					ResCodeMessage.TOTAL_PRICE_MISMATCH.getMessage());
		}

		// ================= 3. 判斷完畢，開始建立訂單資料（OrdersDto） =================
		OrdersDto orders = new OrdersDto();
		orders.setOrdersType(req.getOrdersType());
		orders.setOrdersDate(req.getOrdersDate());
		orders.setOrdersTime(req.getOrdersTime());
		orders.setTotalPrice(req.getTotalPrice());
		orders.setPaymentType(req.getPaymentType());
		orders.setPaid(req.isPaid());
		orders.setCustomerName(req.getCustomerName());
		orders.setCustomerPhone(req.getCustomerPhone());
		orders.setCustomerAddress(req.getCustomerAddress());
		orders.setTableId(req.getTableId());

		// INSERT 新增訂單
		ordersDao.addOrder(orders);
		// 拿到 AI 生成的 ordersId
		int ordersId = orders.getOrdersId();
		// 後端運算code
		String orderCode = generateOrderCode(ordersId, req.getOrdersType(), // A，T，D
				req.getOrdersDate().toString(), // yyyy-MM-dd
				req.getOrdersTime().toString() // HH:mm:ss
		);
		System.out.println("運算完成的結果" + orderCode);
		// 回寫 ordersCode 到資料庫
		ordersDao.updateOrdersCode(ordersId, orderCode);

		// 將orderDetail細節放入
		for (OrderDetailReq detailReq : req.getOrderDetailsList()) {

			// OrderDetailDto稍晚存放結果
			OrderDetailDto detail = new OrderDetailDto();
			detail.setOrderDetailsId(detailReq.getOrderDetailsId());
			detail.setOrderDetailsPrice(detailReq.getOrderDetailsPrice());
			detail.setSettingId(detailReq.getSettingId());
			detail.setOrdersId(ordersId);
			// 將細節的json轉成string存入
			String jsonString = mapper.writeValueAsString(detailReq.getOrderDetails());
			detail.setOrderDetails(jsonString);
			// 將細節傳回
			ordersDao.addOrderDetail(detail);
		}

		return new BasicRes(//
				ResCodeMessage.SUCCESS.getCode(), //
				ResCodeMessage.SUCCESS.getMessage());
	}
}
