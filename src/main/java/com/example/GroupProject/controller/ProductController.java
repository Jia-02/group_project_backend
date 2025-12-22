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
import com.example.GroupProject.response.ProductAllDetailRes;
import com.example.GroupProject.response.ProductRes;
import com.example.GroupProject.service.ProductService;

@RestController
@CrossOrigin 
//(origins = "http://192.168.0.174:4200")
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
	
	//刪除商品
	@PostMapping(value = "product/del")
	public BasicRes delProductById(@RequestBody ProductDto dto) {
		return productService.delProductById(dto);
	}
	
	//更新商品
	@PostMapping(value = "product/update")
	public BasicRes updateProduct(@RequestBody ProductDto dto) {
		return productService.updateProduct(dto);
	}
	
	//查詢商品，使用者點餐(顯示商品+客製化)
	@GetMapping(value = "product/detail")
	public ProductAllDetailRes getProductById( //
			@RequestParam("categoryId") int categoryId, //
			@RequestParam("productId") int productId ) throws Exception  {
		return productService.getProductById(categoryId, productId);
	}
}
