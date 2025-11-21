//package com.example.GroupProject.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.GroupProject.request.AddProductReq;
//import com.example.GroupProject.response.BasicRes;
//import com.example.GroupProject.response.ProductRes;
//import com.example.GroupProject.service.ProductService;
//
//@RestController
//@CrossOrigin
//public class ProductController {
//	
//	@Autowired
//	private ProductService productService;
//	
//	@PostMapping(value = "product/add")
//	public BasicRes addProduct(@RequestBody AddProductReq req) {
//		return productService.addProduct(req);
//	}
//	
//	@GetMapping(value = "product/list")
//	public ProductRes getProductList(){
//		return productService.getProductList();
//	}
//	
//	
//}
