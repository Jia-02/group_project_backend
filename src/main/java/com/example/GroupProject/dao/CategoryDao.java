package com.example.GroupProject.dao;

import java.util.List;

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
	
	//使用中的分類
	public int checkCategoryUsed(@Param("categoryId") int categoryId);
	
	//分類ID是否存在
	public int checkCategoryExist(@Param("categoryId") int categoryId);
	
	//更新分類
	public int updateCategory(CategoryDto categoryDto);
	
	//查詢分類列表
	public List<CategoryDto> getCategoryList();

}
