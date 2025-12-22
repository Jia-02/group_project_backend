package com.example.GroupProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.GroupProject.dto.SettingDto;
import com.example.GroupProject.request.SettingBasicReq;
import com.example.GroupProject.response.BasicRes;
import com.example.GroupProject.response.SettingAllDetailRes;
import com.example.GroupProject.response.SettingListRes;
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
	
	//透過分類Id，查詢套餐列表(管理者)
	@GetMapping(value = "setting/list")
	public SettingListRes getSettingListById(@RequestParam("categoryId") int categoryId) throws Exception{
		return settingService.getSettingListById(categoryId);
	}
	
	//透過分類，查詢套餐列表(使用者)getUserSettingListById
	@GetMapping(value = "setting/list/user")
	public SettingListRes getUserSettingListById(@RequestParam("categoryId") int categoryId) throws Exception{
		return settingService.getUserSettingListById(categoryId);
	}
	
	//查詢單筆套餐所有資訊(productId取得商品內容、categoryId取得客製化內容) (使用者)
	@GetMapping(value = "setting/detail")
	public SettingAllDetailRes getSettingAllDetailById(@RequestParam("settingId") int settingId) throws Exception{
		return settingService.getSettingAllDetailById(settingId);
	}

}
