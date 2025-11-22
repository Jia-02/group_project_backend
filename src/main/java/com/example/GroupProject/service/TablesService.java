package com.example.GroupProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.GroupProject.constants.ResCodeMessage;
import com.example.GroupProject.dao.TablesDao;
import com.example.GroupProject.dto.TablesDto;
import com.example.GroupProject.response.BasicRes;
import com.example.GroupProject.response.TableListRes;

@Service
public class TablesService {
	
	@Autowired
	private TablesDao tableDao;
	
	public BasicRes addTable(TablesDto table) {
		
		tableDao.addTable(table);
		
		return new BasicRes(ResCodeMessage.SUCCESS.getCode(),ResCodeMessage.SUCCESS.getMessage());
	}
	
	public TableListRes getTableList() {
		return new TableListRes(ResCodeMessage.SUCCESS.getCode(),ResCodeMessage.SUCCESS.getMessage(),tableDao.getTableList());
	}
	
}
