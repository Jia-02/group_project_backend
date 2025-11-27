package com.example.GroupProject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.GroupProject.dto.ProductDto;

@Mapper
public interface ProductDao {
	
	//新增商品
	public int addProduct(ProductDto productDto);
	
	//查看商品列表(管理者)
	public List<ProductDto> getProductList(@Param("categoryId") int categoryId);
	
	//查看商品列表(使用者)
	public List<ProductDto> getUserProductList(@Param("categoryId") int categoryId);

}
