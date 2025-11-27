package com.example.GroupProject.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.GroupProject.dto.ProductDto;
import com.example.GroupProject.response.BasicRes;
import com.example.GroupProject.response.ProductRes;
import com.example.GroupProject.service.ProductService;

@RestController
@CrossOrigin
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	//新增商品
	@PostMapping(value = "product/add")
	public BasicRes addProduct(@RequestBody ProductDto dto) {
		return productService.addProduct(dto);
	}
	
	//查看商品列表(管理者)
	@GetMapping(value = "product/list")
	public ProductRes getProductList(@RequestParam("categoryId") int categoryId){
		return productService.getProductList(categoryId);
	}
	
	//查看商品列表(使用者)
	@GetMapping(value = "product/list/user")
	public ProductRes getUserProductList(@RequestParam("categoryId") int categoryId){
		return productService.getUserProductList(categoryId);
	}
	
	
}
