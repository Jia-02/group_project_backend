package com.example.GroupProject.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.GroupProject.dto.CategoryDto;


@Mapper
public interface CategoryDao {
	
	//新增分類
	public int addCategory(CategoryDto categoryDto);
	
	//確認分類是否已存在
	public boolean checkCategoryExists( //
			@Param("categoryType") String categoryType, //
			@Param("workstationId") int workstationId);
	
	//刪除分類
	public int delCategoryById(CategoryDto categoryDto);
	
	//更新分類
	public int updateCategoryById(CategoryDto categoryDto);

}
