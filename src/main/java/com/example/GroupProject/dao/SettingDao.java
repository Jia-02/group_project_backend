package com.example.GroupProject.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.GroupProject.dto.SettingDto;

@Mapper
public interface SettingDao {
	
	//新增套餐
	public int addSetting(SettingDto settingDto);
	
	//重覆套餐名稱
	public boolean checkSettingName(@Param("settingName") String settingName);
	
	//刪除套餐
	public int delSettingById(SettingDto settingDto);
	
	//確認該套餐存在
	public int checkSettingExist(@Param("settingId") int settingId);
	
	//透過套餐id取得單筆套餐資料
	public SettingDto getSettingById(@Param("settingId") int settingId);

	//透過分類id取得套餐
	public SettingDto getSettingListById(@Param("categoryId") int categoryId);
	
}
