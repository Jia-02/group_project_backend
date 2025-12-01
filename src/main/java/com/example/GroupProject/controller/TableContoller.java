package com.example.GroupProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.GroupProject.dto.TableDailyDto;
import com.example.GroupProject.dto.TablesDto;
import com.example.GroupProject.response.BasicRes;
import com.example.GroupProject.response.TableListRes;
import com.example.GroupProject.service.TablesService;

import jakarta.validation.Valid;

@CrossOrigin // 允許前端來源
@RestController
public class TableContoller {
	
	@Autowired
	private TablesService tableService;
	
	@PostMapping(value = "table/add")
	public BasicRes addTable(@Valid @RequestBody TablesDto table) {
		return tableService.addTable(table);
	}
	
	@GetMapping(value = "table/list")
	public TableListRes getTableList() {
		return tableService.getTableList();
	}
	
	@PostMapping(value = "table/del")
	public BasicRes delTable(@RequestBody TablesDto table) {
		return tableService.delTable(table);
	}
	
	//新增桌位狀態
	@PostMapping(value = "table/status/add")
	public BasicRes insertTableStatus(@RequestBody TableDailyDto table) {
		return tableService.insertTableStatus(table);
	}
	
	//更新桌位狀態
	@PostMapping(value = "table/status/update")
	public BasicRes updateTableStatus(@RequestBody TableDailyDto table) {
		return tableService.updateTableStatus(table);
	}
	
	//刪除桌位狀態
	@PostMapping(value = "table/status/del")
	public BasicRes delTableStatus(@RequestBody TableDailyDto table) {
		return tableService.delTableStatus(table);
	}
	
}
