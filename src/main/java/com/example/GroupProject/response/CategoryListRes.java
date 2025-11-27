package com.example.GroupProject.response;

import java.util.List;

import com.example.GroupProject.dto.CategoryDto;

public class CategoryListRes extends BasicRes {
	
	private List<CategoryDto> categoryDto;

	public CategoryListRes() {
		super();
	}

	public CategoryListRes(int code, String message) {
		super(code, message);
	}

	public CategoryListRes(int code, String message, List<CategoryDto> categoryDto) {
		super(code, message);
		this.categoryDto = categoryDto;
	}

	public List<CategoryDto> getCategoryDto() {
		return categoryDto;
	}

	public void setCategoryDto(List<CategoryDto> categoryDto) {
		this.categoryDto = categoryDto;
	}

}
