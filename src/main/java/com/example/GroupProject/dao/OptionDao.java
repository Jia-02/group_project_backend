package com.example.GroupProject.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.GroupProject.dto.OptionDto;

@Mapper
public interface OptionDao {

	//新增客製化
	public int addOption(OptionDto optionDto);
	
	//刪除客製化
	public int delOptionById(OptionDto optionDto);
	
	//確認該客製化存在
	public int checkOptionExist(@Param("optionId") int optionId);
}
