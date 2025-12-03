package com.example.GroupProject.vo;

import java.util.List;

import com.example.GroupProject.dto.SettingDetailDto;

public class SettingVo {

	private int settingId;

	private String settingName;

	List<SettingDetailDto> settingDetail;

	private int settingPrice;

	private String settingImg;

	private boolean settingActive;

	private String settingNote;

	public SettingVo() {
		super();
	}

	public SettingVo(int settingId, String settingName, List<SettingDetailDto> settingDetail, int settingPrice,
			String settingImg, boolean settingActive, String settingNote) {
		super();
		this.settingId = settingId;
		this.settingName = settingName;
		this.settingDetail = settingDetail;
		this.settingPrice = settingPrice;
		this.settingImg = settingImg;
		this.settingActive = settingActive;
		this.settingNote = settingNote;
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

}
