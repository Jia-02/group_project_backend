package com.example.GroupProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.GroupProject.constants.ResCodeMessage;
import com.example.GroupProject.dao.ProductDao;
import com.example.GroupProject.dto.ProductDto;
import com.example.GroupProject.response.BasicRes;
import com.example.GroupProject.response.ProductRes;

@Service
public class ProductService {
	
	@Autowired
	private ProductDao productDao;
	
	//新增商品
	@Transactional(rollbackFor = Exception.class)
	public BasicRes addProduct(ProductDto dto) {
		productDao.addProduct(dto);
		
		return new BasicRes(ResCodeMessage.SUCCESS.getCode(),ResCodeMessage.SUCCESS.getMessage());
	}
	
	//查看商品
	@Transactional(rollbackFor = Exception.class)
	public ProductRes getProductList() {
		return new ProductRes( //
				ResCodeMessage.SUCCESS.getCode(), //
				ResCodeMessage.SUCCESS.getMessage(), //
				productDao.getProductList());
	}
	
}
