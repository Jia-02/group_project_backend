package com.example.GroupProject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.GroupProject.dto.ProductDto;

@Mapper
public interface ProductDao {
	
	//新增商品
	public int addProduct(ProductDto productDto);
	
	//查看商品列表
	public List<ProductDto> getProductList();

}
