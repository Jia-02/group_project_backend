package com.example.GroupProject.response;

import java.util.List;

import com.example.GroupProject.dto.SettingCategoryDetailDto;

public class SettingAllDetailRes extends BasicRes {
	
	private int categoryId;
	
	private int settingId;

	private String settingName;

	private int settingPrice;

	private String settingImg;

	private boolean settingActive;

	private String settingNote;
	
	List<SettingCategoryDetailDto> settingDetail;

	public SettingAllDetailRes() {
		super();
	}

	public SettingAllDetailRes(int code, String message) {
		super(code, message);
	}

	public SettingAllDetailRes(int code, String message, int categoryId, int settingId, String settingName,
			int settingPrice, String settingImg, boolean settingActive, String settingNote,
			List<SettingCategoryDetailDto> settingDetail) {
		super(code, message);
		this.categoryId = categoryId;
		this.settingId = settingId;
		this.settingName = settingName;
		this.settingPrice = settingPrice;
		this.settingImg = settingImg;
		this.settingActive = settingActive;
		this.settingNote = settingNote;
		this.settingDetail = settingDetail;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getSettingId() {
		return settingId;
	}

	public void setSettingId(int settingId) {
		this.settingId = settingId;
	}

	public String getSettingName() {
		return settingName;
	}

	public void setSettingName(String settingName) {
		this.settingName = settingName;
	}

	public int getSettingPrice() {
		return settingPrice;
	}

	public void setSettingPrice(int settingPrice) {
		this.settingPrice = settingPrice;
	}

	public String getSettingImg() {
		return settingImg;
	}

	public void setSettingImg(String settingImg) {
		this.settingImg = settingImg;
	}

	public boolean isSettingActive() {
		return settingActive;
	}

	public void setSettingActive(boolean settingActive) {
		this.settingActive = settingActive;
	}

	public String getSettingNote() {
		return settingNote;
	}

	public void setSettingNote(String settingNote) {
		this.settingNote = settingNote;
	}

	public List<SettingCategoryDetailDto> getSettingDetail() {
		return settingDetail;
	}

	public void setSettingDetail(List<SettingCategoryDetailDto> settingDetail) {
		this.settingDetail = settingDetail;
	}

}
