package com.example.GroupProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.example.GroupProject.constants.ResCodeMessage;
import com.example.GroupProject.dao.CategoryDao;
import com.example.GroupProject.dao.ProductDao;
import com.example.GroupProject.dto.ProductDto;
import com.example.GroupProject.response.BasicRes;
import com.example.GroupProject.response.ProductRes;

@Service
public class ProductService {

	@Autowired
	private ProductDao productDao;

	@Autowired
	private CategoryDao categoryDao;

	// 新增商品
	@Transactional(rollbackFor = Exception.class)
	public BasicRes addProduct(ProductDto dto) {

		// 分類ID不存在
		if (categoryDao.checkCategoryExist(dto.getCategoryId()) == 0) {
			return new BasicRes(//
					ResCodeMessage.CATEGORY_IS_NOT_FOUND.getCode(), //
					ResCodeMessage.CATEGORY_IS_NOT_FOUND.getMessage());
		}

		// 餐點名稱、敘述、圖片網址不可 null，且至少非一個空白字元
		if (!StringUtils.hasText(dto.getProductName()) || //
				!StringUtils.hasText(dto.getProductDescription()) ||//
				!StringUtils.hasText(dto.getImageUrl())) {
			return new BasicRes( //
					ResCodeMessage.PRODUCT_ERROR.getCode(), //
					ResCodeMessage.PRODUCT_ERROR.getMessage());
		}

		// 價格不可以小於0
		if (dto.getProductPrice() <= 0) {
			return new BasicRes( //
					ResCodeMessage.PRODUCT_PRICE_ERROR.getCode(), //
					ResCodeMessage.PRODUCT_PRICE_ERROR.getMessage());
		}

		int result = productDao.addProduct(dto);
		if (result > 0) {
			return new BasicRes(//
					ResCodeMessage.SUCCESS.getCode(), //
					ResCodeMessage.SUCCESS.getMessage());
		} else {
			return new BasicRes(//
					ResCodeMessage.CREATE_PRODUCT_FAILED.getCode(), //
					ResCodeMessage.CREATE_PRODUCT_FAILED.getMessage());
		}
	}

	// 查看商品
	@Transactional(rollbackFor = Exception.class)
	public ProductRes getProductList(int categoryId) {
		return new ProductRes( //
				ResCodeMessage.SUCCESS.getCode(), //
				ResCodeMessage.SUCCESS.getMessage(), //
				productDao.getProductList(categoryId));
	}

}
