package com.example.GroupProject.dto;

import java.util.List;

public class SettingDetailDto {
	
	private int categoryId;
	private List<SettingDetailProductDto> detailList;
	
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public List<SettingDetailProductDto> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<SettingDetailProductDto> detailList) {
		this.detailList = detailList;
	}
	
	

}
