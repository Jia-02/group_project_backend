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

	// 新增桌位
	public BasicRes addTable(TablesDto table) {
		

		for (TablesDto item : tableDao.getTableList()) {
			// 迴圈檢查存在中的桌位ID是否有與新增的桌位ID重複
			if (table.getTableId().equalsIgnoreCase(item.getTableId())) {
				return new BasicRes(ResCodeMessage.TABLE_ID_EXIST.getCode(),
						ResCodeMessage.TABLE_ID_EXIST.getMessage());
			}
			// 迴圈檢查該位置是否已存在桌位
			if (item.getTablePositionX() >= table.getTablePositionX() && //
					item.getTablePositionX() <= table.getTablePositionX() + 20) {
				if (item.getTablePositionY() >= table.getTablePositionY() && //
						item.getTablePositionY() <= table.getTablePositionY() + 20) {
					return new BasicRes(ResCodeMessage.TABLE_POSITION_EXIST.getCode(),
							ResCodeMessage.TABLE_POSITION_EXIST.getMessage());
				} else if (item.getTablePositionY() <= table.getTablePositionY() && //
						item.getTablePositionY() + 20 >= table.getTablePositionY()) {
					return new BasicRes(ResCodeMessage.TABLE_POSITION_EXIST.getCode(),
							ResCodeMessage.TABLE_POSITION_EXIST.getMessage());
				}
			} else if (item.getTablePositionX() <= table.getTablePositionX() && //
					item.getTablePositionX() + 20 >= table.getTablePositionX()) {
				if (item.getTablePositionY() >= table.getTablePositionY() && //
						item.getTablePositionY() <= table.getTablePositionY() + 20) {
					return new BasicRes(ResCodeMessage.TABLE_POSITION_EXIST.getCode(),
							ResCodeMessage.TABLE_POSITION_EXIST.getMessage());
				} else if (item.getTablePositionY() <= table.getTablePositionY() && //
						item.getTablePositionY() + 20 >= table.getTablePositionY()) {
					return new BasicRes(ResCodeMessage.TABLE_POSITION_EXIST.getCode(),
							ResCodeMessage.TABLE_POSITION_EXIST.getMessage());
				}
			}
		}

		tableDao.addTable(table);

		return new BasicRes(ResCodeMessage.SUCCESS.getCode(), ResCodeMessage.SUCCESS.getMessage());
	}

	// list查詢
	public TableListRes getTableList() {
		
		if(tableDao.getTableList().isEmpty()) {
			return new TableListRes(ResCodeMessage.TABLE_NOT_FOUND.getCode(), ResCodeMessage.TABLE_NOT_FOUND.getMessage());
		}
		
		return new TableListRes(ResCodeMessage.SUCCESS.getCode(), ResCodeMessage.SUCCESS.getMessage(),
				tableDao.getTableList());
	}

	// 刪除桌位
	public BasicRes delTable(TablesDto table) {
		
		tableDao.delTableByTableId(table);

		return new BasicRes(ResCodeMessage.SUCCESS.getCode(), ResCodeMessage.SUCCESS.getMessage());
	}

	// 更新桌位
	public BasicRes updateTable(TablesDto table) {
		
		tableDao.updateByTableId(table);

		return new BasicRes(ResCodeMessage.SUCCESS.getCode(), ResCodeMessage.SUCCESS.getMessage());
	}

}
