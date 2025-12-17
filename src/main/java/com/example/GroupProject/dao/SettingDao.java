package com.example.GroupProject.dao;

import java.util.List;

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
	
	//透過id取得名稱
	public String getSettingName(@Param("settingId") int settingId);
	
	//透過分類id取得套餐(管理者)
	public List<SettingDto> getSettingListById(@Param("categoryId") int categoryId);
	
	//透過分類id取得套餐(使用者)
	public List<SettingDto> getUserSettingListById(@Param("categoryId") int categoryId);
	
	//透過套餐id取得單筆套餐資料(管理者)
	public SettingDto getSettingById(@Param("settingId") int settingId);
	
	//查詢商品是否使用中
	public int checkProductUsedInSetting(@Param("productId") int productId);

	
}
