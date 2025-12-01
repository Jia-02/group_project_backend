package com.example.GroupProject.response;

import java.util.List;

import com.example.GroupProject.vo.OptionVo;


public class OptionListRes extends BasicRes {
	
	private int categoryId;
	private List<OptionVo> optionVoList;
	
	public OptionListRes() {
		super();
	}
	public OptionListRes(int code, String message) {
		super(code, message);
	}
	public OptionListRes(int code, String message, int categoryId, List<OptionVo> optionVoList) {
		super(code, message);
		this.categoryId = categoryId;
		this.optionVoList = optionVoList;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public List<OptionVo> getOptionVoList() {
		return optionVoList;
	}
	public void setOptionVoList(List<OptionVo> optionVoList) {
		this.optionVoList = optionVoList;
	}

}
