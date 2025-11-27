package com.example.GroupProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.GroupProject.dto.CategoryDto;
import com.example.GroupProject.response.BasicRes;
import com.example.GroupProject.service.CategoryService;

@CrossOrigin // 允許前端來源
@RestController
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	//新增分類
	@PostMapping(value = "category/add")
	public BasicRes addTable(@RequestBody CategoryDto categoryDto) {
		return categoryService.addCategory(categoryDto);
	}
	
	//刪除分類
	@PostMapping(value = "category/del")
	public BasicRes delCategoryById(@RequestBody CategoryDto categoryDto) {
		return categoryService.delCategoryById(categoryDto);
	}
	
	//更新分類
	@PostMapping(value = "category/update")
	public BasicRes updateCategory(@RequestBody CategoryDto categoryDto) {
		return categoryService.updateCategory(categoryDto);
	}
}
