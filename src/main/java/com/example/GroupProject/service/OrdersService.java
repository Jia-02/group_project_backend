package com.example.GroupProject.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.example.GroupProject.constants.ResCodeMessage;
import com.example.GroupProject.dao.CategoryDao;
import com.example.GroupProject.dao.MealStatusDao;
import com.example.GroupProject.dao.OptionDao;
import com.example.GroupProject.dao.OrdersDao;
import com.example.GroupProject.dao.ProductDao;
import com.example.GroupProject.dao.SettingDao;
import com.example.GroupProject.dao.TablesDao;
import com.example.GroupProject.dto.CategoryDto;
import com.example.GroupProject.dto.MealStatusDto;
import com.example.GroupProject.dto.OptionDetailDto;
import com.example.GroupProject.dto.OptionDto;
import com.example.GroupProject.dto.OrderDetailDto;
import com.example.GroupProject.dto.OrdersDto;
import com.example.GroupProject.dto.ProductDto;
import com.example.GroupProject.dto.SettingDto;
import com.example.GroupProject.request.AddOrdersReq;
import com.example.GroupProject.request.OrderDetailReq;
import com.example.GroupProject.request.OrderProductReq;
import com.example.GroupProject.request.OrderUpdateReq;
import com.example.GroupProject.response.BasicRes;
import com.example.GroupProject.response.OrdersAllDetailRes;
import com.example.GroupProject.response.OrdersListRes;
import com.example.GroupProject.response.OrdersMealListRes;
import com.example.GroupProject.response.OrdersMealRes;
import com.example.GroupProject.vo.OrderMealDetailVo;
import com.example.GroupProject.vo.OrdersMealListVo;
import com.example.GroupProject.vo.OrdersMealVo;
import com.example.GroupProject.vo.OrdersVo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OrdersService {

	// json跟java物件的轉換
	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private OrdersDao ordersDao;

	@Autowired
	private OptionDao optionDao;

	@Autowired
	private TablesDao tableDao;

	@Autowired
	private SettingDao settingDao;

	@Autowired
	private ProductDao productDao;

	@Autowired
	private CategoryDao categoryDao;

	@Autowired
	private MealStatusDao mealStatusDao;

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

	// 私有方法檢查資訊
	private BasicRes checkInfo(AddOrdersReq req) {
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

		// 如果付款方式不是現金或取消，已結帳
		if (!"現金".equals(req.getPaymentType()) && !req.isPaid() && !"取消".equals(req.getPaymentType())) {
			return new BasicRes(ResCodeMessage.NOT_CASH_CANT_NO_PAID.getCode(), //
					ResCodeMessage.NOT_CASH_CANT_NO_PAID.getMessage());
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
			int settingId = detail.getSettingId();
			int detailPrice = 0;

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
			if (settingId > 0) {
				if (settingDao.checkSettingExist(settingId) == 0) {
					return new BasicRes(ResCodeMessage.SETTING_NOT_FOUND.getCode(),
							ResCodeMessage.SETTING_NOT_FOUND.getMessage());
				}
				SettingDto setting = settingDao.getSettingById(settingId);
				detailPrice += setting.getSettingPrice();
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

				// 套餐價格
				if (settingId <= 0) { // 非套餐
					detailPrice += product.getProductPrice();
				}

				List<OptionDetailDto> options = product.getDetailList();
				// 客製化細節不可為空
				if (options == null) {
					return new BasicRes(ResCodeMessage.OPTION_EMPTY.getCode(),
							ResCodeMessage.OPTION_EMPTY.getMessage());
				}
				// 檢查客製化
				for (OptionDetailDto option : options) {
					String userOptionName = option.getOption();
					int userAddPrice = option.getAddPrice();
					// OptionName不可為空
					if (!StringUtils.hasText(userOptionName)) {
						return new BasicRes(ResCodeMessage.OPTION_DETAIL_NAME_EMPTY.getCode(),
								ResCodeMessage.OPTION_DETAIL_NAME_EMPTY.getMessage());
					}
					// 稍晚判斷客製化是否存在
					boolean matched = false;
					// category 有多個 option
					List<OptionDto> optionListFromDb = optionDao.getOptionList(categoryId);
					// 迴圈檢查客製化
					for (OptionDto dbOption : optionListFromDb) {
						// 字串成 list
						List<OptionDetailDto> optionDetailList = new ArrayList<>();
						try {
							ObjectMapper mapper = new ObjectMapper();
							optionDetailList = mapper.readValue(dbOption.getOptionDetail(),
									new TypeReference<List<OptionDetailDto>>() {
									});
						} catch (Exception e) {
							return new BasicRes(ResCodeMessage.OPTION_DETAIL_EMPTY.getCode(),
									ResCodeMessage.OPTION_DETAIL_EMPTY.getMessage());
						}

						for (OptionDetailDto dbDetail : optionDetailList) {

							// 使用者提供名稱 是否與 分類中客製化名稱匹配
							if (dbDetail.getOption().equals(userOptionName)) {
								matched = true;
								// 價格匹配
								if (dbDetail.getAddPrice() != userAddPrice) {
									return new BasicRes(ResCodeMessage.OPTION_DETAIL_PRICE_ERROR.getCode(),
											ResCodeMessage.OPTION_DETAIL_PRICE_ERROR.getMessage());
								}
								break;
							}
						}
						if (matched)
							break;
					}

					// 全部 option 都沒找到 → 非法的客製化選項
					if (!matched) {
						return new BasicRes(ResCodeMessage.OPTION_NOT_FOUND.getCode(),
								ResCodeMessage.OPTION_NOT_FOUND.getMessage());
					}
					detailPrice += option.getAddPrice();
				}
			}
			// 算出來的價格不等於前端輸入
			if (detailPrice != detail.getOrderDetailsPrice()) {
				return new BasicRes(ResCodeMessage.ORDER_DETAIL_PRICE_ERROR.getCode(),
						ResCodeMessage.ORDER_DETAIL_PRICE_ERROR.getMessage());
			}
			detail.setOrderDetailsPrice(detailPrice); // 更新明細價格
			calcTotalPrice += detail.getOrderDetailsPrice(); // 計算單筆細節總價格
		}

		// 驗證前端 totalPrice 是否正確
		if (calcTotalPrice != req.getTotalPrice()) {
			return new BasicRes(ResCodeMessage.TOTAL_PRICE_MISMATCH.getCode(),
					ResCodeMessage.TOTAL_PRICE_MISMATCH.getMessage());
		}

		return null;
	}

	// 新增訂單
	@Transactional(rollbackFor = Exception.class)
	public BasicRes addOrder(AddOrdersReq req) throws Exception {

		// 共同方法判斷
		BasicRes checkInfoRes = checkInfo(req);
		if (checkInfoRes != null) {
			return checkInfoRes;
		}

		// 如果付款方式是取消，不可新增
		if ("取消".equals(req.getPaymentType())) {
			return new BasicRes(ResCodeMessage.NOT_CASH_CANT_NO_PAID.getCode(), //
					ResCodeMessage.NOT_CASH_CANT_NO_PAID.getMessage());
		}

		// ================= 判斷完畢，開始建立訂單資料（OrdersDto） =================
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

		LocalTime finishTime = req.getOrdersTime();
		int estimatedTime = 0;

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

			// 根據product數量增加製造所需時間 每個product 5分鐘
			for (OrderProductReq orderProduct : detailReq.getOrderDetails()) {
				estimatedTime += 5;
			}

		}

		// ---------MealStatus新增部分開始------------

		finishTime = finishTime.plusMinutes(estimatedTime);

		MealStatusDto mealStatus = new MealStatusDto("製作中", estimatedTime, finishTime, ordersId);

		mealStatusDao.addMealStatus(mealStatus);

		// ---------MealStatus新增部分結束-------------

		return new BasicRes(//
				ResCodeMessage.SUCCESS.getCode(), //
				ResCodeMessage.SUCCESS.getMessage());
	}

	// 更新訂單 (尚未付款時，可改訂單明細，或是取消訂單)
	@Transactional(rollbackFor = Exception.class)
	public BasicRes updateOrderInNoPaid(AddOrdersReq req) throws Exception {

		int ordersId = req.getOrdersId();
		// 訂單編號 >= 0
		if (ordersId <= 0) {
			return new BasicRes(//
					ResCodeMessage.ORDERS_ID_ERROR.getCode(), //
					ResCodeMessage.ORDERS_ID_ERROR.getMessage());
		}

		// 確認訂單是否存在
		if (ordersDao.checkOrdersExist(ordersId) == 0) {
			return new BasicRes(//
					ResCodeMessage.ORDERS_NOT_FOUND.getCode(), //
					ResCodeMessage.ORDERS_NOT_FOUND.getMessage());
		}

		// 查詢舊訂單
		OrdersDto oldOrder = ordersDao.getOrdersById(ordersId);
		if (oldOrder == null) {
			return new BasicRes(ResCodeMessage.ORDERS_NOT_FOUND.getCode(),
					ResCodeMessage.ORDERS_NOT_FOUND.getMessage());
		}

		// 已經付款不可更新
		if (oldOrder.isPaid()) {
			return new BasicRes(ResCodeMessage.IS_PAID.getCode(), //
					ResCodeMessage.IS_PAID.getMessage());
		}

		// 只有payment_type為現金，且未付款時，可以更新訂單
		if (!"現金".equals(oldOrder.getPaymentType())) {
			return new BasicRes(ResCodeMessage.NOT_CASH_CANT_UPDATE.getCode(), //
					ResCodeMessage.NOT_CASH_CANT_UPDATE.getMessage());
		}

		// ======= 檢查不可修改的欄位 =======
		if (!oldOrder.getOrdersDate().equals(req.getOrdersDate())
				|| !oldOrder.getOrdersType().equals(req.getOrdersType())
				|| !oldOrder.getOrdersTime().equals(req.getOrdersTime())
				|| !Objects.equals(oldOrder.getTableId(), req.getTableId())
				|| !Objects.equals(oldOrder.getCustomerName(), req.getCustomerName())
				|| !Objects.equals(oldOrder.getCustomerPhone(), req.getCustomerPhone())
				|| !Objects.equals(oldOrder.getCustomerAddress(), req.getCustomerAddress())) {
			return new BasicRes(ResCodeMessage.BASIC_INFO_CANT_UPDATE.getCode(),
					ResCodeMessage.BASIC_INFO_CANT_UPDATE.getMessage());
		}

		// 共同方法判斷
		BasicRes checkInfoRes = checkInfo(req);
		if (checkInfoRes != null) {
			return checkInfoRes;
		}

		// 更新訂單
		OrdersDto updated = new OrdersDto();
		updated.setOrdersId(ordersId);
		updated.setTotalPrice(req.getTotalPrice());
		updated.setPaid(req.isPaid());
		updated.setPaymentType(req.getPaymentType());
		ordersDao.updateOrder(updated);

		// ======= 刪除舊明細，新增新明細 =======
		int deleted = ordersDao.delOrderDetailById(ordersId);
		if (deleted == 0) {
			return new BasicRes(ResCodeMessage.DELETE_ORDERS_FAILED.getCode(),
					ResCodeMessage.DELETE_ORDERS_FAILED.getMessage());
		}
		for (OrderDetailReq detailReq : req.getOrderDetailsList()) {
			OrderDetailDto detail = new OrderDetailDto();
			detail.setOrderDetailsId(detailReq.getOrderDetailsId());
			detail.setOrderDetailsPrice(detailReq.getOrderDetailsPrice());
			detail.setSettingId(detailReq.getSettingId());
			detail.setOrdersId(ordersId);
			String jsonString = mapper.writeValueAsString(detailReq.getOrderDetails());
			detail.setOrderDetails(jsonString);
			ordersDao.addOrderDetail(detail);
		}

		return new BasicRes(//
				ResCodeMessage.SUCCESS.getCode(), //
				ResCodeMessage.SUCCESS.getMessage());
	}

	// 更新訂單 (管理者針對餐點狀態)
	@Transactional(rollbackFor = Exception.class)
	public BasicRes updateOrderIsPaid(OrderUpdateReq req) throws Exception {
		int ordersId = req.getOrdersId();

		if (ordersId <= 0 || ordersDao.checkOrdersExist(ordersId) == 0) {
			return new BasicRes(ResCodeMessage.ORDERS_NOT_FOUND.getCode(),
					ResCodeMessage.ORDERS_NOT_FOUND.getMessage());
		}

		OrdersDto oldOrder = ordersDao.getOrdersById(ordersId);
		if (oldOrder == null || !oldOrder.isPaid()) {
			return new BasicRes(ResCodeMessage.IS_NOT_PAID.getCode(), ResCodeMessage.IS_NOT_PAID.getMessage());
		}

		int countStatus = 0;
		List<String> orderStatusList = new ArrayList<>();

		// 只更新餐點細節
		for (OrderDetailReq detailReq : req.getOrderDetails()) {
			String jsonString = mapper.writeValueAsString(detailReq.getOrderDetails());
			ordersDao.updateOrderIsPaid(ordersId, detailReq.getOrderDetailsId(), jsonString);
			for (OrderProductReq productReq : detailReq.getOrderDetails()) {
				if (productReq.getMealStatus().equalsIgnoreCase("製作中")) {
					countStatus++;
				}
				orderStatusList.add(productReq.getMealStatus());
			}
		}

		MealStatusDto mealStatus = mealStatusDao.getMealStatus(ordersId);
		String orderStatus = "已送達";
		if (orderStatusList.contains("製作中")) {
			orderStatus = "製作中";
		} else if (orderStatusList.contains("待送餐")) {
			orderStatus = "待送餐";
		}
		
		LocalTime orderTime = LocalTime.now();
		mealStatus.setEstimatedTime(5 * countStatus);
		mealStatus.setMealStatus(orderStatus);
		mealStatus.setFinishTime(orderTime.plusMinutes(mealStatus.getEstimatedTime()));

		mealStatusDao.updateMealStatus(mealStatus);

		return new BasicRes(ResCodeMessage.SUCCESS.getCode(), ResCodeMessage.SUCCESS.getMessage());
	}

	// 查詢訂單列表(訂單管理者用)
	@Transactional(readOnly = true)
	public OrdersListRes getOrdersList() {
		// 查詢所有訂單（不含明細）
		List<OrdersVo> ordersList = ordersDao.getOrdersList();

		return new OrdersListRes(//
				ResCodeMessage.SUCCESS.getCode(), ResCodeMessage.SUCCESS.getMessage(), //
				ordersList);
	}

	// 透過ordersId查詢單筆訂單資訊與細節
	@Transactional(readOnly = true)
	public OrdersAllDetailRes getOrdersAllDetailById(int ordersId) throws Exception {

		// 取得資訊
		OrdersDto dto = ordersDao.getOrdersById(ordersId);
		if (dto == null) {
			return new OrdersAllDetailRes(ResCodeMessage.ORDERS_NOT_FOUND.getCode(),
					ResCodeMessage.ORDERS_NOT_FOUND.getMessage());
		}

		// 取得明細列表
		List<OrderDetailDto> dbDetailList = ordersDao.getOrderDetailById(ordersId);

		// 不可為null
		if (dbDetailList == null || dbDetailList.isEmpty()) {
			return new OrdersAllDetailRes(ResCodeMessage.ORDER_DETAIL_EMPTY.getCode(),
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
				productList = mapper.readValue(jsonString, new TypeReference<List<OrderProductReq>>() {
				});
			}

			detailReq.setOrderDetails(productList);

			// 加入最終列表
			finalList.add(detailReq);
		}

		// 5. 組合 final Res
		return new OrdersAllDetailRes(ResCodeMessage.SUCCESS.getCode(), ResCodeMessage.SUCCESS.getMessage(),
				dto.getOrdersId(), dto.getOrdersType(), dto.getOrdersDate(), dto.getOrdersTime(), dto.getTotalPrice(),
				dto.getPaymentType(), dto.isPaid(), dto.getOrdersCode(), dto.getCustomerName(), dto.getCustomerPhone(),
				dto.getCustomerAddress(), dto.getTableId(), finalList);
	}

	// 透過ordersId查詢單筆訂單資訊與細節，餐點狀態、工作台用
	@Transactional(readOnly = true)
	public OrdersMealRes getOrdersMealById(int ordersId) throws Exception {

		// 1. 查詢訂單基本資料
		OrdersDto dto = ordersDao.getOrdersById(ordersId);
		if (dto == null) {
			return new OrdersMealRes(ResCodeMessage.ORDERS_NOT_FOUND.getCode(),
					ResCodeMessage.ORDERS_NOT_FOUND.getMessage());
		}

		// 2. 查詢訂單明細列表
		List<OrderDetailDto> dbDetailList = ordersDao.getOrderDetailById(ordersId);

		if (dbDetailList == null || dbDetailList.isEmpty()) {
			return new OrdersMealRes(ResCodeMessage.ORDER_DETAIL_EMPTY.getCode(),
					ResCodeMessage.ORDER_DETAIL_EMPTY.getMessage());
		}

		// ★ 最後要放的明細
		List<OrdersMealVo> finalList = new ArrayList<>();

		// 3. 逐筆處理 OrderDetail
		for (OrderDetailDto detail : dbDetailList) {

			OrdersMealVo mealVo = new OrdersMealVo();
			mealVo.setOrderDetailsId(detail.getOrderDetailsId());
			mealVo.setOrderDetailsPrice(detail.getOrderDetailsPrice());
			mealVo.setSettingId(detail.getSettingId());

			// 解析 OrderDetails (JSON → List<OrderMealDetailVo>)
			String jsonString = detail.getOrderDetails();
			List<OrderMealDetailVo> productList = new ArrayList<>();

			if (StringUtils.hasText(jsonString)) {
				// 轉成 ProductReq（含客製化 detailList）
				List<OrderProductReq> tempList = mapper.readValue(jsonString,
						new TypeReference<List<OrderProductReq>>() {
						});

				// ★ 再轉成 OrderMealDetailVo（加入 workStationId）
				for (OrderProductReq p : tempList) {

					OrderMealDetailVo mealDetail = new OrderMealDetailVo();
					mealDetail.setCategoryId(p.getCategoryId());
					mealDetail.setProductId(p.getProductId());
					mealDetail.setProductName(p.getProductName());
					mealDetail.setProductPrice(p.getProductPrice());
					mealDetail.setMealStatus(p.getMealStatus());
					mealDetail.setDetailList(p.getDetailList()); // 客製化直接帶入

					// ★ 加入 workstationId（查 category）
					CategoryDto cat = categoryDao.getCategoryById(p.getCategoryId());
					if (cat != null) {
						mealDetail.setWorkStationId(cat.getWorkstationId());
					}

					productList.add(mealDetail);
				}
			}

			mealVo.setOrderDetails(productList); // 設定商品資料
			finalList.add(mealVo);
		}

		// 4. 組合回傳資料
		return new OrdersMealRes(ResCodeMessage.SUCCESS.getCode(), ResCodeMessage.SUCCESS.getMessage(),
				dto.getOrdersId(), dto.getOrdersType(), dto.getOrdersDate(), dto.getOrdersTime(), dto.getTotalPrice(),
				dto.getPaymentType(), dto.isPaid(), dto.getOrdersCode(), dto.getCustomerName(), dto.getCustomerPhone(),
				dto.getCustomerAddress(), dto.getTableId(), finalList);
	}

	// 日期查詢當日訂單列表+細節
	@Transactional(readOnly = true)
	public OrdersMealListRes getOrdersMealByDate(LocalDate ordersDate) throws Exception {

		// 1. 查詢當日所有訂單
		List<OrdersDto> ordersList = ordersDao.getOrdersByDate(ordersDate);

		if (ordersList == null || ordersList.isEmpty()) {
			return new OrdersMealListRes(ResCodeMessage.ORDERS_NOT_FOUND.getCode(),
					ResCodeMessage.ORDERS_NOT_FOUND.getMessage(), null);
		}
		List<OrdersMealListVo> finalOrders = new ArrayList<>();

		// 2. 逐筆處理每張訂單
		for (OrdersDto dto : ordersList) {

			int ordersId = dto.getOrdersId();
			// 查詢訂單明細
			List<OrderDetailDto> dbDetailList = ordersDao.getOrderDetailById(ordersId);
			List<OrdersMealVo> finalDetailList = new ArrayList<>();

			// 3. 處理每筆明細
			for (OrderDetailDto detail : dbDetailList) {

				OrdersMealVo mealVo = new OrdersMealVo();
				mealVo.setOrderDetailsId(detail.getOrderDetailsId());
				mealVo.setOrderDetailsPrice(detail.getOrderDetailsPrice());
				mealVo.setSettingId(detail.getSettingId());

				// 解析 JSON → 商品列表
				String jsonString = detail.getOrderDetails();
				List<OrderMealDetailVo> productList = new ArrayList<>();
				if (StringUtils.hasText(jsonString)) {

					// 先轉成 OrderProductReq
					List<OrderProductReq> tempList = mapper.readValue(jsonString,
							new TypeReference<List<OrderProductReq>>() {
							});

					// 再轉成 OrderMealDetailVo
					for (OrderProductReq p : tempList) {
						OrderMealDetailVo mealDetail = new OrderMealDetailVo();
						mealDetail.setCategoryId(p.getCategoryId());
						mealDetail.setProductId(p.getProductId());
						mealDetail.setProductName(p.getProductName());
						mealDetail.setProductPrice(p.getProductPrice());
						mealDetail.setMealStatus(p.getMealStatus());
						mealDetail.setDetailList(p.getDetailList()); // 客製化

						// workstationId
						CategoryDto cat = categoryDao.getCategoryById(p.getCategoryId());
						if (cat != null) {
							mealDetail.setWorkStationId(cat.getWorkstationId());
						}
						productList.add(mealDetail);
					}
				}

				mealVo.setOrderDetails(productList);
				finalDetailList.add(mealVo);
			}

			// 使用 OrdersMealListVo
			OrdersMealListVo listVo = new OrdersMealListVo();
			listVo.setOrdersId(dto.getOrdersId());
			listVo.setOrdersType(dto.getOrdersType());
			listVo.setOrdersDate(dto.getOrdersDate());
			listVo.setOrdersTime(dto.getOrdersTime());
			listVo.setTotalPrice(dto.getTotalPrice());
			listVo.setPaymentType(dto.getPaymentType());
			listVo.setPaid(dto.isPaid());
			listVo.setOrdersCode(dto.getOrdersCode());
			listVo.setCustomerName(dto.getCustomerName());
			listVo.setCustomerPhone(dto.getCustomerPhone());
			listVo.setCustomerAddress(dto.getCustomerAddress());
			listVo.setTableId(dto.getTableId());
			listVo.setOrderDetailsList(finalDetailList);
			finalOrders.add(listVo);
		}

		// 4. 回傳結果
		return new OrdersMealListRes(ResCodeMessage.SUCCESS.getCode(), ResCodeMessage.SUCCESS.getMessage(),
				finalOrders);
	}

}
