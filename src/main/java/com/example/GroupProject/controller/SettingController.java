package com.example.GroupProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.GroupProject.dto.SettingDto;
import com.example.GroupProject.request.SettingBasicReq;
import com.example.GroupProject.response.BasicRes;
import com.example.GroupProject.service.SettingService;

@RestController
@CrossOrigin
public class SettingController {
	
	@Autowired
	private SettingService settingService;
	
	//新增套餐
	@PostMapping(value = "setting/add")
	public BasicRes addSetting(@RequestBody SettingBasicReq req) throws Exception{
		return settingService.addSetting(req);
	}
	
	//刪除套餐
	@PostMapping(value = "setting/del")
	public BasicRes delSettingById(@RequestBody SettingDto dto) {
		return settingService.delSettingById(dto);
	}
	
	//更新套餐
	@PostMapping(value = "setting/update")
	public BasicRes updateSetting(@RequestBody SettingBasicReq req) throws Exception{
		return settingService.updateSetting(req);
	}
	
	//透過分類Id，查詢套餐
	

}
