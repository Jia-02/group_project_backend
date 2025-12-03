package com.example.GroupProject.dto;

public class SettingDto {

    private int settingId;

    private String settingName;

    private String settingDetail;

    private int settingPrice;

    private String settingImg;

    // 1 (啟用) / 0 (關閉)
    private boolean settingActive; 

    private String settingNote;

    private int categoryId;

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

	public String getSettingDetail() {
		return settingDetail;
	}

	public void setSettingDetail(String settingDetail) {
		this.settingDetail = settingDetail;
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

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
    
}