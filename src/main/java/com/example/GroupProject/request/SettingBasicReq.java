package com.example.GroupProject.request;

import java.util.List;

import com.example.GroupProject.dto.SettingDetailDto;

public class SettingBasicReq {
	
    private int settingId;

    private String settingName;

    private List<SettingDetailDto> settingDetail;

    private int settingPrice;

    private String settingImg;

    private boolean settingActive; 

    private String settingNote;

    private int categoryId;
    
    

	public SettingBasicReq() {
		super();
	}

	public SettingBasicReq(int settingId, String settingName, List<SettingDetailDto> settingDetail, int settingPrice,
			String settingImg, boolean settingActive, String settingNote, int categoryId) {
		super();
		this.settingId = settingId;
		this.settingName = settingName;
		this.settingDetail = settingDetail;
		this.settingPrice = settingPrice;
		this.settingImg = settingImg;
		this.settingActive = settingActive;
		this.settingNote = settingNote;
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

	public List<SettingDetailDto> getSettingDetail() {
		return settingDetail;
	}

	public void setSettingDetail(List<SettingDetailDto> settingDetail) {
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
