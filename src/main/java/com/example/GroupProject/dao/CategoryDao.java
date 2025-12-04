package com.example.GroupProject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.GroupProject.dto.CategoryDto;


@Mapper
public interface CategoryDao {
	
	//新增分類
	public int addCategory(CategoryDto categoryDto);
	
	//分類名稱是否重覆
	public boolean checkCategoryName(@Param("categoryType") String categoryType);
	
	//刪除分類
	public int delCategoryById(CategoryDto categoryDto);
	
	//商品使用中的分類
	public int checkProductCategoryUsed(@Param("categoryId") int categoryId);
	
	//客製化使用中的分類
	public int checkOptionCategoryUsed(@Param("categoryId") int categoryId);
	
	//分類ID是否存在
	public int checkCategoryExistById(@Param("categoryId") int categoryId);
	
	//更新分類
	public int updateCategory(CategoryDto categoryDto);
	
	//查詢分類列表
	public List<CategoryDto> getCategoryList();
	
	//查詢單一分類
	public CategoryDto getCategoryById(@Param("categoryId") int categoryId);

}
