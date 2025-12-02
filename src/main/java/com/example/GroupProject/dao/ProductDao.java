package com.example.GroupProject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.GroupProject.dto.ProductDto;
import com.example.GroupProject.vo.ProductVo;

@Mapper
public interface ProductDao {
	
	//新增商品
	public int addProduct(ProductDto productDto);
	
	//查看商品列表(管理者)
	public List<ProductVo> getProductList(@Param("categoryId") int categoryId);
	
	//查看商品列表(使用者)
	public List<ProductVo> getUserProductList(@Param("categoryId") int categoryId);
	
	//刪除商品
	public int delProductById(ProductDto dto);
	
	//確認商品存在數量
	public int checkProductExist(@Param("productId") int productId);
	
	//商品名稱是否重複
	public boolean checkProductName(@Param("productName") String productName);
	
	//確認商品是否上架中
	public boolean getProductActive(@Param("productId") int productId);

	//更新商品
	public int updateProduct(ProductDto productDto);
	
	//透過產品id取得其他資訊
	public ProductDto getDetailByProductId(@Param("productId") int productId);
	
}
