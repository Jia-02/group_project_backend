package com.example.GroupProject.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.example.GroupProject.constants.ResCodeMessage;
import com.example.GroupProject.dao.CategoryDao;
import com.example.GroupProject.dao.OptionDao;
import com.example.GroupProject.dao.ProductDao;
import com.example.GroupProject.dao.SettingDao;
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
	
	@Autowired
	private SettingDao settingDao;
	
	//ai過敏原
	private final ChatClient chatClient;

    public ProductService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }
    
    public String allergenCreateByObject(ProductDto productAiVo) {

		if (!StringUtils.hasText(productAiVo.getProductName())) {
			productAiVo.setProductName("");
		}

		if (!StringUtils.hasText(productAiVo.getProductDescription())) {
			productAiVo.setProductDescription("");
		}

		if (!StringUtils.hasText(productAiVo.getProductNote())) {
			productAiVo.setProductNote("");
		}

		// 1. 先生成一串30~50字的餐點描述 以利於後續的過敏原判斷

		String prompt01 = "{  \"productName\": \"" + productAiVo.getProductName() + "\", \"productDescription\":\""
				+ productAiVo.getProductDescription() + "\", \"productNote\": \" " + productAiVo.getProductNote()
				+ "\" }";
		System.out.println("prompt : " + prompt01);

		String str1st = "只根據這個json中的productName與productDescription與productNote，盡量依照這幾個欄位的字面意思，並參考維基百科對該項目的描述，生成30~50字左右的餐點描述，著重與餐點源料或成分相關的描述。";

		String newPrompt = prompt01 + str1st;
		// 直接使用提示詞

		ChatResponse response = chatClient.prompt(newPrompt).call().chatResponse();
		System.out.println("第一次生成 : " + response.getResult().getOutput().getText());

		// 2. 根據餐點名稱與新生成的字串去做AI過敏原分析

		StringBuilder resSb = new StringBuilder("");
		// 分析到有為止 迴圈
		while (true) {
			String prompt02 = "餐點名稱:" + productAiVo.getProductName() + "，餐點描述:"
					+ response.getResult().getOutput().getText();
			String str2nd = "根據這個餐點名稱與餐點描述，列出所有可能的過敏源，過敏源只需寫出過敏原名稱，多個過敏源間用、隔開，不用分析詳情只留最後結果。輸出範例格式: 可能的過敏源:過敏原1、過敏原2......。如果真的沒有過敏源，就輸出:可能的過敏源:";

			String newPrompt02 = prompt02 + str2nd;
			response = chatClient.prompt(newPrompt02).call().chatResponse();
			String finalRes = response.getResult().getOutput().getText();
			System.out.println("第二次生成 : " + finalRes);

			if (finalRes.contentEquals("無") && finalRes.length() < 8) {
				System.out.println("因為" + finalRes + "，重新生成中......");
				continue;
			}

			resSb.append(finalRes);
			break;
		}
		return resSb.toString();
	}


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
		//ai過敏原
		String aiText = allergenCreateByObject(dto);
		dto.setProductDescription(dto.getProductDescription() + aiText);

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

	// 透過分類查看商品 (管理者)
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
				categoryId, productList);
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
				categoryId, productList);
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
		if (productDao.checkProductExist(dto.getCategoryId(), dto.getProductId()) == 0) {
			return new BasicRes(ResCodeMessage.PRODUCT_NOT_FOUND.getCode(),
					ResCodeMessage.PRODUCT_NOT_FOUND.getMessage());
		}

		// 如果商品上架中，不可刪除
		if (productDao.getProductActive(dto.getCategoryId(), dto.getProductId())) {
			return new BasicRes( //
					ResCodeMessage.PRODUCT_IS_USED.getCode(), //
					ResCodeMessage.PRODUCT_IS_USED.getMessage());
		}
		
		//如果套餐內有該商品，不可刪除
		if (settingDao.checkProductUsedInSetting(dto.getProductId()) > 0) {
		    return new BasicRes(
		        ResCodeMessage.SETTING_IS_USED.getCode(),
		        ResCodeMessage.SETTING_IS_USED.getMessage()
		    );
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
		if (productDao.checkProductExist(dto.getCategoryId(), dto.getProductId()) == 0) {
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
		
		//如果套餐有該商品，修改部分不可為下架商品
		boolean oldActive = productDao.getProductActive(dto.getCategoryId(), dto.getProductId());
		boolean newActive = dto.isProductActive(); // 前端傳進來的狀態

		if (oldActive && !newActive) {
		    // 原本是上架，現在要下架
		    if (settingDao.checkProductUsedInSetting(dto.getProductId()) > 0) {
		        return new BasicRes(
						ResCodeMessage.SETTING_IS_USED.getCode(), //
						ResCodeMessage.SETTING_IS_USED.getMessage());
		    }
		}

		String aiText = allergenCreateByObject(dto);
		dto.setProductDescription(dto.getProductDescription() + aiText);
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

	// 查詢單樣商品，使用者點餐(顯示商品+客製化)
	@Transactional(readOnly = true)
	public ProductAllDetailRes getProductById(int categoryId, int productId) throws Exception {

		ProductDto dto = productDao.getDetailByProductId(categoryId, productId);

		// 商品不存在
		if (dto == null) {
			return new ProductAllDetailRes(ResCodeMessage.PRODUCT_NOT_FOUND.getCode(),
					ResCodeMessage.PRODUCT_NOT_FOUND.getMessage());
		}

		// 判斷本身與傳輸之分類id是否相等
		if (dto.getCategoryId() != categoryId) {
			return new ProductAllDetailRes(//
					ResCodeMessage.PRODUCT_AND_CATEGORY_NOT_MATCH.getCode(), //
					ResCodeMessage.PRODUCT_AND_CATEGORY_NOT_MATCH.getMessage());
		}

		// 分類不存在
		if (categoryDao.checkCategoryExistById(categoryId) == 0) {
			return new ProductAllDetailRes(ResCodeMessage.CATEGORY_IS_NOT_FOUND.getCode(),
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

				List<OptionDetailDto> detailList = mapper.readValue(jsonDetail,
						new TypeReference<List<OptionDetailDto>>() {
						});
				vo.setOptionDetail(detailList);
			}
			optionList.add(vo);
		}

		// 5. 回傳結果
		return new ProductAllDetailRes(ResCodeMessage.SUCCESS.getCode(), ResCodeMessage.SUCCESS.getMessage(),
				categoryId, dto.getProductId(), dto.getProductName(), dto.getProductPrice(), dto.isProductActive(),
				dto.getProductDescription(), dto.getImageUrl(), dto.getProductNote(), category.getCategoryType(),
				category.getWorkstationId(), optionList);
	}

}
