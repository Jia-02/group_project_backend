package com.example.GroupProject.vo;

import java.util.List;

import com.example.GroupProject.dto.OptionDetailDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class OptionVo {
	
    private int optionId;
    private String optionName;
    private int maxSelect;
    private List<OptionDetailDto> optionDetail;
    
    @JsonIgnore
    private String optionDetailJson; // 暫存 JSON 字串
    
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

	public List<OptionDetailDto> getOptionDetail() {
		return optionDetail;
	}
	public void setOptionDetail(List<OptionDetailDto> optionDetail) {
		this.optionDetail = optionDetail;
	}
	public int getMaxSelect() {
		return maxSelect;
	}
	public void setMaxSelect(int maxSelect) {
		this.maxSelect = maxSelect;
	}
	public String getOptionDetailJson() {
		return optionDetailJson;
	}
	public void setOptionDetailJson(String optionDetailJson) {
		this.optionDetailJson = optionDetailJson;
	}

}
