package com.example.GroupProject.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.example.GroupProject.constants.ResCodeMessage;
import com.example.GroupProject.dao.CategoryDao;
import com.example.GroupProject.dao.OptionDao;
import com.example.GroupProject.dao.ProductDao;
import com.example.GroupProject.dto.CategoryDto;
import com.example.GroupProject.dto.OptionDetailDto;
import com.example.GroupProject.dto.ProductDto;
import com.example.GroupProject.response.BasicRes;
import com.example.GroupProject.response.ProductAllDetailRes;
import com.example.GroupProject.response.ProductRes;
import com.example.GroupProject.vo.OptionVo;
import com.example.GroupProject.vo.ProductVo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ProductService {

	// json跟java物件的轉換
	private ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	private ProductDao productDao;

	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private OptionDao optionDao;

	// 新增商品
	@Transactional(rollbackFor = Exception.class)
	public BasicRes addProduct(ProductDto dto) {
		
		// 確認商品id是否 > 0
		if (dto.getProductId() <= 0) {
			return new BasicRes(//
					ResCodeMessage.PRODUCT_ID_ERROR.getCode(), //
					ResCodeMessage.PRODUCT_ID_ERROR.getMessage());
		}

		// 分類ID不存在
		if (categoryDao.checkCategoryExistById(dto.getCategoryId()) == 0) {
			return new BasicRes(//
					ResCodeMessage.CATEGORY_IS_NOT_FOUND.getCode(), //
					ResCodeMessage.CATEGORY_IS_NOT_FOUND.getMessage());
		}

		// 餐點名稱、敘述、圖片網址不可 null，且至少非一個空白字元
		if (!StringUtils.hasText(dto.getProductName()) || //
				!StringUtils.hasText(dto.getProductDescription()) || //
				!StringUtils.hasText(dto.getImageUrl())) {
			return new BasicRes( //
					ResCodeMessage.PRODUCT_NAME_ERROR.getCode(), //
					ResCodeMessage.PRODUCT_NAME_ERROR.getMessage());
		}

		// 價格不可以小於0
		if (dto.getProductPrice() <= 0) {
			return new BasicRes( //
					ResCodeMessage.PRODUCT_PRICE_ERROR.getCode(), //
					ResCodeMessage.PRODUCT_PRICE_ERROR.getMessage());
		}
		
		//商品名稱重複
		if(productDao.checkProductName(dto.getProductName())) {
			return new BasicRes( //
					ResCodeMessage.PRODUCT_NAME_IS_USED.getCode(), //
					ResCodeMessage.PRODUCT_NAME_IS_USED.getMessage());
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

	// 查看商品 (管理者)
	@Transactional(readOnly = true)
	public ProductRes getProductList(int categoryId) {

		// 分類ID不存在
		if (categoryDao.checkCategoryExistById(categoryId) == 0) {
			return new ProductRes(//
					ResCodeMessage.CATEGORY_IS_NOT_FOUND.getCode(), //
					ResCodeMessage.CATEGORY_IS_NOT_FOUND.getMessage());
		}
		List<ProductVo> productList = productDao.getProductList(categoryId);
		
		return new ProductRes( //
				ResCodeMessage.SUCCESS.getCode(), //
				ResCodeMessage.SUCCESS.getMessage(), //
				categoryId,
				productList);
	}

	// 查看商品列表(點餐時)-不顯示active=0
	@Transactional(readOnly = true)
	public ProductRes getUserProductList(int categoryId) {

		// 分類ID不存在
		if (categoryDao.checkCategoryExistById(categoryId) == 0) {
			return new ProductRes(//
					ResCodeMessage.CATEGORY_IS_NOT_FOUND.getCode(), //
					ResCodeMessage.CATEGORY_IS_NOT_FOUND.getMessage());
		}
		
		List<ProductVo> productList = productDao.getUserProductList(categoryId);

		return new ProductRes( //
				ResCodeMessage.SUCCESS.getCode(), //
				ResCodeMessage.SUCCESS.getMessage(), //
				categoryId,
				productList);
	}

	// 刪除商品
	@Transactional(rollbackFor = Exception.class)
	public BasicRes delProductById(ProductDto dto) {

		// 確認商品id是否 > 0
		if (dto.getProductId() <= 0) {
			return new BasicRes(//
					ResCodeMessage.PRODUCT_ID_ERROR.getCode(), //
					ResCodeMessage.PRODUCT_ID_ERROR.getMessage());
		}

		// 確認商品存在
		if (productDao.checkProductExist(dto.getProductId()) == 0) {
			return new BasicRes(ResCodeMessage.PRODUCT_NOT_FOUND.getCode(),
					ResCodeMessage.PRODUCT_NOT_FOUND.getMessage());
		}

		// 如果商品上架中，不可刪除
		if (productDao.getProductActive(dto.getProductId())) {
			return new BasicRes( //
					ResCodeMessage.PRODUCT_IS_USED.getCode(), //
					ResCodeMessage.PRODUCT_IS_USED.getMessage());
		}

		int result = productDao.delProductById(dto);
		if (result > 0) {
			return new BasicRes(//
					ResCodeMessage.SUCCESS.getCode(), //
					ResCodeMessage.SUCCESS.getMessage());
		} else {
			return new BasicRes(//
					ResCodeMessage.DELETE_PRODUCT_FAILED.getCode(), //
					ResCodeMessage.DELETE_PRODUCT_FAILED.getMessage());
		}

	}

	// 更新商品
	@Transactional(rollbackFor = Exception.class)
	public BasicRes updateProduct(ProductDto dto) {

		// 確認商品id是否 > 0
		if (dto.getProductId() <= 0) {
			return new BasicRes(//
					ResCodeMessage.PRODUCT_ID_ERROR.getCode(), //
					ResCodeMessage.PRODUCT_ID_ERROR.getMessage());
		}

		// 確認商品存在
		if (productDao.checkProductExist(dto.getProductId()) == 0) {
			return new BasicRes(ResCodeMessage.PRODUCT_NOT_FOUND.getCode(),
					ResCodeMessage.PRODUCT_NOT_FOUND.getMessage());
		}

		// 分類ID不存在
		if (categoryDao.checkCategoryExistById(dto.getCategoryId()) == 0) {
			return new BasicRes(//
					ResCodeMessage.CATEGORY_IS_NOT_FOUND.getCode(), //
					ResCodeMessage.CATEGORY_IS_NOT_FOUND.getMessage());
		}

		// 餐點名稱、敘述、圖片網址不可 null，且至少非一個空白字元
		if (!StringUtils.hasText(dto.getProductName()) || //
				!StringUtils.hasText(dto.getProductDescription()) || //
				!StringUtils.hasText(dto.getImageUrl())) {
			return new BasicRes( //
					ResCodeMessage.PRODUCT_NAME_ERROR.getCode(), //
					ResCodeMessage.PRODUCT_NAME_ERROR.getMessage());
		}

		// 價格不可以小於0
		if (dto.getProductPrice() <= 0) {
			return new BasicRes( //
					ResCodeMessage.PRODUCT_PRICE_ERROR.getCode(), //
					ResCodeMessage.PRODUCT_PRICE_ERROR.getMessage());
		}

		int result = productDao.updateProduct(dto);
		if (result > 0) {
			return new BasicRes(//
					ResCodeMessage.SUCCESS.getCode(), //
					ResCodeMessage.SUCCESS.getMessage());
		} else {
			return new BasicRes(//
					ResCodeMessage.UPDATE_PRODUCT_FAILED.getCode(), //
					ResCodeMessage.UPDATE_PRODUCT_FAILED.getMessage());
		}
	}
	
	//查詢單樣商品，使用者點餐(顯示商品+客製化)
	@Transactional(readOnly = true)
	public ProductAllDetailRes getProductById(int productId) throws Exception  {

		ProductDto dto = productDao.getDetailByProductId(productId);
		
		// 商品不存在
		if (dto == null) {
			return new ProductAllDetailRes(
					ResCodeMessage.PRODUCT_NOT_FOUND.getCode(),
					ResCodeMessage.PRODUCT_NOT_FOUND.getMessage());
		}
		
		// 2. 取得分類 ID
		int categoryId = dto.getCategoryId();

		// 分類不存在
		if (categoryDao.checkCategoryExistById(categoryId) == 0) {
			return new ProductAllDetailRes(
					ResCodeMessage.CATEGORY_IS_NOT_FOUND.getCode(),
					ResCodeMessage.CATEGORY_IS_NOT_FOUND.getMessage());
		}
		
		// 3. 查分類基本資料
		CategoryDto category = categoryDao.getCategoryById(categoryId);

		// 4. 查分類底下所有客製化
		List<OptionVo> voList = optionDao.getOptionListByCategoryId(categoryId);
		List<OptionVo> optionList = new ArrayList<>();

		for (OptionVo optionDto : voList) {

			OptionVo vo = new OptionVo();
			vo.setOptionId(optionDto.getOptionId());
			vo.setOptionName(optionDto.getOptionName());
			vo.setMaxSelect(optionDto.getMaxSelect());

			// 解析 JSON → List<OptionDetailDto>
			String jsonDetail = optionDto.getOptionDetailJson();
			if (StringUtils.hasText(jsonDetail)) {

				List<OptionDetailDto> detailList = mapper.readValue(
						jsonDetail, new TypeReference<List<OptionDetailDto>>() {});
				vo.setOptionDetail(detailList);
			}
			optionList.add(vo);
		}

		// 5. 回傳結果
		return new ProductAllDetailRes(
				ResCodeMessage.SUCCESS.getCode(),
				ResCodeMessage.SUCCESS.getMessage(),
				categoryId,
				dto.getProductId(),
				dto.getProductName(),
				dto.getProductPrice(),
				dto.isProductActive(),
				dto.getProductDescription(),
				dto.getImageUrl(),
				dto.getProductNote(),
				category.getCategoryType(),
				category.getWorkstationId(),
				optionList
		);
	}
	
	
}
