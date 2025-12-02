package com.example.GroupProject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.GroupProject.dto.WorkStationDto;

@Mapper
public interface WorkStationDao {
	
	public void addWorkStation(@Param("workStationName") String workStationName);

	public List<WorkStationDto> getWorkStationList();
	
	public int deleteWorkStation(@Param("workStationId") int workStationId);
	
}
