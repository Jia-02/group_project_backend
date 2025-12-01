package com.example.GroupProject.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface WorkStationDao {
	
	public void addWorkStation(@Param("workStationName") String workStationName);

}
