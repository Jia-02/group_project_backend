package com.example.GroupProject.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.GroupProject.dto.OptionDto;

@Mapper
public interface OptionDao {

	//新增客製化
	public int addOption(OptionDto optionDto);
}
