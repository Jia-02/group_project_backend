package com.example.GroupProject.response;

import java.util.List;

import com.example.GroupProject.vo.SettingVo;

public class SettingListRes extends BasicRes {

	private int categoryId;
	private List<SettingVo> optionVoList;

	public SettingListRes() {
		super();
	}

	public SettingListRes(int code, String message) {
		super(code, message);
	}

	public SettingListRes(int code, String message, int categoryId, List<SettingVo> optionVoList) {
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

	public List<SettingVo> getOptionVoList() {
		return optionVoList;
	}

	public void setOptionVoList(List<SettingVo> optionVoList) {
		this.optionVoList = optionVoList;
	}

}
