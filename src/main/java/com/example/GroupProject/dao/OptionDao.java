package com.example.GroupProject.dao;

import java.util.List;

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
	
	//客製化名稱重複
	public boolean checkOptionName(@Param("optionName") String optionName);
	
	//查詢客製化列表(透過分類ID)
	public List<OptionDto> getOptionList(@Param("categoryId") int categoryId);
}
