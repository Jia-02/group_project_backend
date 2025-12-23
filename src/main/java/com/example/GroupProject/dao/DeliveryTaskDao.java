package com.example.GroupProject.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.GroupProject.dto.DeliveryTask;

@Mapper
public interface DeliveryTaskDao {

	public List<DeliveryTask> getAll();

	public List<DeliveryTask> getAllNotEnd();

	public List<DeliveryTask> getAllNoDeliveryId();

	public int getDeliveryUserCountById(@Param(value = "id") int deliveryId);
	
	public void updateDeliveryIdToDTask(@Param(value = "orderNo") String orderNo,@Param(value = "deliveryId") int deliveryId);
	
	public List<DeliveryTask> getTaskListTaking(@Param(value = "deliveryId") int deliveryId);
	
	public List<DeliveryTask> getTaskListByDate(@Param(value = "date") LocalDate date, @Param(value = "deliveryId") int deliveryId);

	public List<DeliveryTask> getTaskListByMonth(@Param(value = "month") int month, @Param(value = "deliveryId") int deliveryId);

	public List<DeliveryTask> getTaskListByYear(@Param(value = "year") int year, @Param(value = "deliveryId") int deliveryId);
	
	public void updateStatus(@Param(value = "orderNo") String orderNo, @Param(value = "status") String status);

	public int getCountByOrderNo(@Param(value = "orderNo") String orderNo);
	
	public List<DeliveryTask> getAllByDeliveryid(@Param(value="deliveryId") int deliveryId);
	
	public  int updateDistanceAndMoney(
	        @Param(value="orderNo") String orderNo,
	        @Param(value="distanceKm") BigDecimal distanceKm,
	        @Param(value="money") BigDecimal money
	    );
	public DeliveryTask getTaskByOrderNo(@Param("orderNo") String orderNo);
	
	public Integer findOrdersIdByOrderNo(String orderNo);
	
}
