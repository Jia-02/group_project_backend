package com.example.GroupProject.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.GroupProject.dto.ReservationDto;

@Mapper
public interface ReservationDao {
	
	//新增訂位
	int createReservation(ReservationDto reservation);

}