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

	
	//新增桌位
	public BasicRes addTable(TablesDto table) {

		tableDao.addTable(table);

		return new BasicRes(ResCodeMessage.SUCCESS.getCode(), ResCodeMessage.SUCCESS.getMessage());
	}

	//list查詢
	public TableListRes getTableList() {
		return new TableListRes(ResCodeMessage.SUCCESS.getCode(), ResCodeMessage.SUCCESS.getMessage(),
				tableDao.getTableList());
	}

	//刪除桌位
	public BasicRes delTable(TablesDto table) {

		tableDao.delTableByTableId(table);

		return new BasicRes(ResCodeMessage.SUCCESS.getCode(), ResCodeMessage.SUCCESS.getMessage());
	}

	//更新桌位
	public BasicRes updateTable(TablesDto table) {

		tableDao.updateByTableId(table);
		
		return new BasicRes(ResCodeMessage.SUCCESS.getCode(), ResCodeMessage.SUCCESS.getMessage());
	}

}
