package com.example.GroupProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.GroupProject.request.OptionCreatReq;
import com.example.GroupProject.response.BasicRes;
import com.example.GroupProject.service.OptionService;

@RestController
@CrossOrigin
public class OptionController {

	@Autowired
	private OptionService optionService;
	
	//新增商品
	@PostMapping(value = "option/add")
	public BasicRes addOption(@RequestBody OptionCreatReq req) throws Exception {
		return optionService.addOption(req);
	}
}
