package com.example.GroupProject.response;

import java.util.List;

import com.example.GroupProject.dto.TablesDto;

public class TableListRes extends BasicRes {

	private List<TablesDto> tableList;

	public TableListRes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TableListRes(int code, String message) {
		super(code, message);
		// TODO Auto-generated constructor stub
	}

	public TableListRes(int code, String message, List<TablesDto> tableList) {
		super(code, message);
		this.tableList = tableList;
	}

	public List<TablesDto> getTableList() {
		return tableList;
	}

	public void setTableList(List<TablesDto> tableList) {
		this.tableList = tableList;
	}

}
