package com.example.GroupProject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.GroupProject.dto.TablesDto;

@Mapper
public interface TablesDao {
	
	public void addTable(TablesDto table);
	
	public List<TablesDto> getTableList();

}
	