package com.example.GroupProject.dto;

import java.util.List;

import com.example.GroupProject.vo.OptionVo;
import com.example.GroupProject.vo.ProductVo;

public class SettingCategoryDetailDto {
	
	private int categoryId; 
	private String categoryType; 
	private int workstationId; 
    private List<ProductVo> detailList;
    private List<OptionVo> optionList;
	public SettingCategoryDetailDto() {
		super();
	}
	public SettingCategoryDetailDto(int categoryId, String categoryType, int workstationId, List<ProductVo> detailList,
			List<OptionVo> optionList) {
		super();
		this.categoryId = categoryId;
		this.categoryType = categoryType;
		this.workstationId = workstationId;
		this.detailList = detailList;
		this.optionList = optionList;
	}
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
	public List<ProductVo> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<ProductVo> detailList) {
		this.detailList = detailList;
	}
	public List<OptionVo> getOptionList() {
		return optionList;
	}
	public void setOptionList(List<OptionVo> optionList) {
		this.optionList = optionList;
	}
   
}
