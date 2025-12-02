package com.example.GroupProject.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.GroupProject.dto.ReservationAndTableByDate;
import com.example.GroupProject.dto.ReservationAndTableByTime;
import com.example.GroupProject.dto.ReservationDto;


@Mapper
public interface ReservationDao {

	// 新增訂位
	public int createReservation(ReservationDto reservation);

	// 查詢訂位列表
	public List<ReservationDto> getReservationList();

	// 查詢該時段之該桌號是否已預定
	public boolean isTableReservedAtSameTime( //
			@Param("tableId") String tableId, //
			@Param("reservationDate") LocalDate reservationDate, //
			@Param("reservationTime") LocalTime reservationTime);

	// 查詢同一天、同電話的預約數量
	public int countByDateAndPhone( //
			@Param("reservationDate") LocalDate reservationDate, //
			@Param("reservationPhone") String reservationPhone);

	// 刪除訂位
	public int deleteReservation( //
			@Param("reservationDate") LocalDate reservationDate, //
			@Param("reservationPhone") String reservationPhone);
	
	// 透過手機號碼和日期找到單筆訂位記錄 (返回一個 Reservation)
	public ReservationDto findByDateAndPhone( //
			@Param("reservationDate") LocalDate reservationDate, //
			@Param("reservationPhone") String reservationPhone);
	
	//透過日期、時間查詢兒童座椅使用狀況
	public int sumChildSeatsByDateAndTime( //
			@Param("reservationDate") LocalDate reservationDate, //
			@Param("reservationTime") LocalTime reservationTime);
	
	//查詢一天的訂位資料(含桌位)
	public List<ReservationAndTableByDate> findReservationsByDate(LocalDate reservationDate);
	
	//查詢同一天某時段之資訊桌位、預約資訊
	public List<ReservationAndTableByTime> findTableStatusByTimeSlot(
		    @Param("reservationDate") LocalDate reservationDate, 
		    @Param("reservationTime") LocalTime reservationTime);
	
	
}