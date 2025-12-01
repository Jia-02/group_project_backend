package com.example.GroupProject.dto;

public class OptionDetailDto {
	
    private String option;
    private int addPrice;
    
	public OptionDetailDto() {
		super();
	}
	public OptionDetailDto(String option, int addPrice) {
		super();
		this.option = option;
		this.addPrice = addPrice;
	}
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	public int getAddPrice() {
		return addPrice;
	}
	public void setAddPrice(int addPrice) {
		this.addPrice = addPrice;
	}

}
