package com.example.GroupProject.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.GroupProject.dto.ReservationDto;

@Mapper
public interface ReservationDao {

	// 新增訂位
	int createReservation(ReservationDto reservation);

	// 查詢訂位列表
	List<ReservationDto> getReservationList();

	// 查詢該時段之該桌號是否已預定
	boolean isTableReservedAtSameTime( //
			@Param("tableId") String tableId, //
			@Param("reservationDate") LocalDate reservationDate, //
			@Param("reservationTime") LocalTime reservationTime);

	// 查詢同一天、同電話的預約數量
	int countByDateAndPhone( //
			@Param("reservationDate") LocalDate reservationDate, //
			@Param("reservationPhone") String reservationPhone);

	// 刪除訂位
	int deleteReservation( //
			@Param("reservationDate") LocalDate reservationDate, //
			@Param("reservationPhone") String reservationPhone);

}