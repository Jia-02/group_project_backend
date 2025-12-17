package com.example.GroupProject.dao;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import com.example.GroupProject.dto.DeliveryTask;
import com.example.GroupProject.dto.OrderDetailDto;
import com.example.GroupProject.dto.OrdersDto;
import com.example.GroupProject.vo.OrdersVo;

@Mapper
public interface OrdersDao {

	// 新增訂單
	// 使資料進入的時候，自動把資料庫產生的主鍵填回 DTO 的 ordersId 屬性
	@Options(useGeneratedKeys = true, keyProperty = "ordersId")
	public int addOrder(OrdersDto ordersDto);

	// 新增訂單明細
	public int addOrderDetail(OrderDetailDto orderDetailDto);

	// 生成訂單編號
	public int updateOrdersCode( //
			@Param("ordersId") int ordersId, //
			@Param("ordersCode") String ordersCode);
	
	//新增外送資訊
	public int addDeliveryTask(DeliveryTask deliveryTask);

	// 確認該套餐存在
	public int checkOrdersExist(@Param("ordersId") int ordersId);

	// ID取得單筆訂單資訊
	public OrdersDto getOrdersById(@Param("ordersId") int ordersId);
	
	// code取得單筆訂單資訊
	public OrdersDto getOrdersByCode(@Param("ordersCode") String ordersCode);
	
	// ID取得列表訂單細節
	public List<OrderDetailDto> getOrderDetailById(@Param("ordersId") int ordersId);

	//查詢訂單列表
	public List<OrdersVo> getOrdersList();
	
	//日期查詢當日訂單列表+細節
	public List<OrdersDto> getOrdersByDate(@Param("ordersDate") LocalDate ordersDate);

	// 更新訂單(未付款前最後編輯，可改為已付款)
	public int updateOrder(OrdersDto ordersDto);

	// 刪除訂單細節
	public int delOrderDetailById(@Param("ordersId") int ordersId);

	// 更新訂單細節中的餐點狀態
	public int updateOrderIsPaid( //
			@Param("ordersId") int ordersId, //
			@Param("orderDetailsId") int orderDetailsId, //
			@Param("orderDetails") String orderDetails);
	
}
