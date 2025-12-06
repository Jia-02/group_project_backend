package com.example.GroupProject.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import com.example.GroupProject.dto.OrderDetailDto;
import com.example.GroupProject.dto.OrdersDto;

@Mapper
public interface OrdersDao {
	
	//新增訂單
	//使資料進入的時候，自動把資料庫產生的主鍵填回 DTO 的 ordersId 屬性
	@Options(useGeneratedKeys = true, keyProperty = "ordersId")
	public int addOrder(OrdersDto ordersDto);
	
	//新增訂單明細
	public int addOrderDetail(OrderDetailDto orderDetailDto);
	
	//生成訂單編號
	public int updateOrdersCode( //
			@Param("ordersId") int ordersId, //
			@Param("ordersCode") String ordersCode);

}
