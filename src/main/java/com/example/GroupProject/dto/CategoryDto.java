package com.example.GroupProject.dto;

public class CategoryDto {
	
	private int categoryId; //菜單分類
	private String categoryType; //菜單種類
	private int workstationId; //工作台Id
	
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryType() {
		return categoryType;
	}
	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}
	public int getWorkstationId() {
		return workstationId;
	}
	public void setWorkstationId(int workstationId) {
		this.workstationId = workstationId;
	}

}
