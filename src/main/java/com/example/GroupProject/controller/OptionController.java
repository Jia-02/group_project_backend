package com.example.GroupProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.GroupProject.dto.OptionDto;
import com.example.GroupProject.request.OptionCreatReq;
import com.example.GroupProject.response.BasicRes;
import com.example.GroupProject.response.OptionListRes;
import com.example.GroupProject.service.OptionService;

@RestController
@CrossOrigin (origins = "http://192.168.0.174:4200")
public class OptionController {

	@Autowired
	private OptionService optionService;
	
	//新增客製化
	@PostMapping(value = "option/add")
	public BasicRes addOption(@RequestBody OptionCreatReq req) throws Exception {
		return optionService.addOption(req);
	}
	
	//刪除客製化
	@PostMapping(value = "option/del")
	public BasicRes delOptionById(@RequestBody OptionDto dto) {
		return optionService.delOptionById(dto);
	}
	
	//更新客製化
	@PostMapping(value = "option/update")
	public BasicRes updateOption(@RequestBody OptionCreatReq req) throws Exception {
		return optionService.updateOption(req);
	}
	
	//查詢客製化列表(透過分類ID)
	@GetMapping(value = "option/list")
	public OptionListRes getOptionList(@RequestParam("categoryId") int categoryId) throws Exception{
		return optionService.getOptionList(categoryId);
	}
}
