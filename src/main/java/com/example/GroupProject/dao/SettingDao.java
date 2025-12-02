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

}
