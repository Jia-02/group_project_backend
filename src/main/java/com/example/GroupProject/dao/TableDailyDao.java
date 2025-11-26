package com.example.GroupProject.dao;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.GroupProject.dto.TableDailyDto;

@Mapper
public interface TableDailyDao {


	public List<TableDailyDto> getDailyStatus(@Param("tableDailyDate") LocalDate tableDailyDate);
	
	//透過日期、桌號查看狀態
	public Integer getTableStatus(//
			@Param("tableDailyDate") LocalDate date,//
			@Param("tableId") String tableId
			);

	public int insertTableStatus(TableDailyDto tableDailyDto);

	public int updateTableStatus(TableDailyDto tableDailyDto);
	
	public int delTableStatus(TableDailyDto tableDailyDto);

}
