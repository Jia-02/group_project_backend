//package com.example.GroupProject.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.example.GroupProject.constants.ResCodeMessage;
//import com.example.GroupProject.dao.ProductDao;
//
//import com.example.GroupProject.request.AddProductReq;
//import com.example.GroupProject.response.BasicRes;
//import com.example.GroupProject.response.ProductRes;
//
//@Service
//public class ProductService {
//	
//	@Autowired
//	private ProductDao productDao;
//	
//	public BasicRes addProduct(AddProductReq req) {
//		productDao.addProduct(req.getWorkTableId(), req.getType(), req.getName(), req.getDescription(), //
//				req.getPrice(), req.getImageUrl() ,req.isActive());
//		
//		return new BasicRes(ResCodeMessage.SUCCESS.getCode(),ResCodeMessage.SUCCESS.getMessage());
//	}
//	
//	public ProductRes getProductList() {
//		
//		return new ProductRes(ResCodeMessage.SUCCESS.getCode(),ResCodeMessage.SUCCESS.getMessage(),productDao.getProductList());
//	}
//	
//}
