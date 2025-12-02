package com.example.GroupProject.request;

import java.util.List;

import com.example.GroupProject.dto.OptionDetailDto;

public class OptionCreatReq {
	
    private int optionId;
    private String optionName;
    private int maxSelect;
    private List<OptionDetailDto> optionDetail; 
    private int categoryId;
    
	public OptionCreatReq() {
		super();
	}
	public OptionCreatReq(int optionId, String optionName, int maxSelect, List<OptionDetailDto> optionDetail,
			int categoryId) {
		super();
		this.optionId = optionId;
		this.optionName = optionName;
		this.maxSelect = maxSelect;
		this.optionDetail = optionDetail;
		this.categoryId = categoryId;
	}
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
	public int getMaxSelect() {
		return maxSelect;
	}
	public void setMaxSelect(int maxSelect) {
		this.maxSelect = maxSelect;
	}
	public List<OptionDetailDto> getOptionDetail() {
		return optionDetail;
	}
	public void setOptionDetail(List<OptionDetailDto> optionDetail) {
		this.optionDetail = optionDetail;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

}
