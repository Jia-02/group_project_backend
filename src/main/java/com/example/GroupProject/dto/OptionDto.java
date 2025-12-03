package com.example.GroupProject.dto;
public class OptionDto {
	
    private int optionId;
    private String optionName;
    private String optionDetail;
    private int maxSelect;
    private int categoryId;
    
	public int getOptionId() {
		return optionId;
	}
	public void setOptionId(int optionId) {
		this.optionId = optionId;
	}
	public String getOptionName() {
		return optionName;
	}
	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}
	public String getOptionDetail() {
		return optionDetail;
	}
	public void setOptionDetail(String optionDetail) {
		this.optionDetail = optionDetail;
	}
	public int getMaxSelect() {
		return maxSelect;
	}
	public void setMaxSelect(int maxSelect) {
		this.maxSelect = maxSelect;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
    

}
