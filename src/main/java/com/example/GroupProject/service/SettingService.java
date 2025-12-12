package com.example.GroupProject.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.example.GroupProject.dto.SettingCategoryDetailDto;
import com.example.GroupProject.dto.SettingDetailDto;
import com.example.GroupProject.dto.SettingDetailProductDto;
import com.example.GroupProject.dto.SettingDto;
import com.example.GroupProject.request.SettingBasicReq;
import com.example.GroupProject.response.BasicRes;
import com.example.GroupProject.response.SettingAllDetailRes;
import com.example.GroupProject.response.SettingListRes;
import com.example.GroupProject.vo.OptionVo;
import com.example.GroupProject.vo.ProductVo;
import com.example.GroupProject.vo.SettingVo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SettingService {

	// json跟java物件的轉換
	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private SettingDao settingDao;

	@Autowired
	private CategoryDao categoryDao;

	@Autowired
	private ProductDao productDao;

	@Autowired
	private OptionDao optionDao;

	// 新增套餐
	@Transactional(rollbackFor = Exception.class)
	public BasicRes addSetting(SettingBasicReq req) throws Exception {

		// 進入的資料判斷
		// 名稱不可為空
		if (!StringUtils.hasText(req.getSettingName())) {
			return new BasicRes(ResCodeMessage.SETTING_NAME_ERROR.getCode(),
					ResCodeMessage.SETTING_NAME_ERROR.getMessage());
		}

		// 設定名稱重複檢查 (假設您有一個 checkSettingName 方法)
		if (settingDao.checkSettingName(req.getSettingName())) {
			return new BasicRes(ResCodeMessage.SETTING_NAME_IS_USED.getCode(),
					ResCodeMessage.SETTING_NAME_IS_USED.getMessage());
		}

		// 價格不可小於0
		if (req.getSettingPrice() <= 0) {
			return new BasicRes(ResCodeMessage.SETTING_PRICE_ERROR.getCode(),
					ResCodeMessage.SETTING_PRICE_ERROR.getMessage());
		}

		// 圖片路徑不可為空
		if (!StringUtils.hasText(req.getSettingImg())) {
			return new BasicRes(ResCodeMessage.SETTING_IMG_ERROR.getCode(),
					ResCodeMessage.SETTING_IMG_ERROR.getMessage());
		}

		// 分類ID存在與否
		if (categoryDao.checkCategoryExistById(req.getCategoryId()) == 0) {
			return new BasicRes(ResCodeMessage.CATEGORY_IS_NOT_FOUND.getCode(),
					ResCodeMessage.CATEGORY_IS_NOT_FOUND.getMessage());
		}

		// 套餐細節列表
		List<SettingDetailDto> detailList = req.getSettingDetail();
		// 套餐細節不可為空
		if (detailList == null || detailList.isEmpty()) {
			return new BasicRes(ResCodeMessage.SETTING_DETAIL_EMPTY.getCode(), //
					ResCodeMessage.SETTING_DETAIL_EMPTY.getMessage());
		}

		// 判斷列表內的細節資料
		for (SettingDetailDto detailCategory : detailList) {
			int categoryId = detailCategory.getCategoryId();
			String categoryType = detailCategory.getCategoryType();

			// 分類id不可小0，null
			if (categoryId <= 0) {
				return new BasicRes(ResCodeMessage.CATEGORY_ID_ERROR.getCode(), //
						ResCodeMessage.CATEGORY_ID_ERROR.getMessage());
			}

			// 分類id存在與否
			if (categoryDao.checkCategoryExistById(categoryId) == 0) {
				return new BasicRes(//
						ResCodeMessage.CATEGORY_IS_NOT_FOUND.getCode(), //
						ResCodeMessage.CATEGORY_IS_NOT_FOUND.getMessage());
			}
			
			//分類名稱存在與否
			if (!categoryDao.checkCategoryName(categoryType)) {
				return new BasicRes(//
						ResCodeMessage.CATEGORY_TYPE_ERROR.getCode(), //
						ResCodeMessage.CATEGORY_TYPE_ERROR.getMessage());
			}

			// 套餐細節商品列表
			List<SettingDetailProductDto> productList = detailCategory.getDetailList();
			// ProductDto是否存在
			if (productList == null || productList.isEmpty()) {
				return new BasicRes(ResCodeMessage.DETAIL_PRODUCT_LIST_EMPTY.getCode(),
						ResCodeMessage.DETAIL_PRODUCT_LIST_EMPTY.getMessage());
			}

			// 同一分類中商品不可重複
			Set<Integer> productIds = new HashSet<>();
			// 遍歷內層 productList
			for (SettingDetailProductDto product : productList) {
				int productId = product.getProductId();
				String productName = product.getProductName();
				
				//產品名稱不可為空或null
				if(!StringUtils.hasText(productName)) {
					return new BasicRes(ResCodeMessage.PRODUCT_NAME_ERROR.getCode(),
							ResCodeMessage.PRODUCT_NAME_ERROR.getMessage());
				}

				// 商品id不可 <= 0
				if (productId <= 0) {
					return new BasicRes(ResCodeMessage.PRODUCT_ID_ERROR.getCode(),
							ResCodeMessage.PRODUCT_ID_ERROR.getMessage());
				}

				// 透過商品id呼叫商品資訊
				ProductDto productDto = productDao.getDetailByProductId(categoryId, productId);

				// 檢查商品是否存在
				if (productDao.checkProductExist(categoryId, productId) == 0) {
					return new BasicRes(ResCodeMessage.PRODUCT_NOT_FOUND.getCode(),
							ResCodeMessage.PRODUCT_NOT_FOUND.getMessage());
				}
				
				//商品未啟用
				if (!productDao.getProductActive(categoryId,productId)) {
					return new BasicRes( //
							ResCodeMessage.PRODUCT_IS_NO_USED.getCode(), //
							ResCodeMessage.PRODUCT_IS_NO_USED.getMessage());
				}


				// 商品細節的 categoryId 與外層 detailCategoryId 匹配
				if (productDto.getCategoryId() != categoryId) {
					return new BasicRes(//
							ResCodeMessage.PRODUCT_AND_CATEGORY_NOT_MATCH.getCode(), //
							ResCodeMessage.PRODUCT_AND_CATEGORY_NOT_MATCH.getMessage());
				}

				// 檢查 product_id 是否重複
				if (!productIds.add(productId)) {
					return new BasicRes(ResCodeMessage.PRODUCT_DUPLICATE.getCode(),
							ResCodeMessage.PRODUCT_DUPLICATE.getMessage());
				}
			}
		}

		// 將json資料轉成字串
		SettingDto dto = new SettingDto();
		// 將資料存入
		dto.setSettingName(req.getSettingName());
		dto.setSettingPrice(req.getSettingPrice());
		dto.setSettingImg(req.getSettingImg());
		dto.setCategoryId(req.getCategoryId());
		dto.setSettingActive(req.isSettingActive()); // 預設值為true
		dto.setSettingNote(req.getSettingNote());

		String jsonString = mapper.writeValueAsString(req.getSettingDetail());
		dto.setSettingDetail(jsonString);

		// 成功通過判斷後新增套餐
		int result = settingDao.addSetting(dto);
		if (result > 0) {
			return new BasicRes(//
					ResCodeMessage.SUCCESS.getCode(), //
					ResCodeMessage.SUCCESS.getMessage());
		} else {
			return new BasicRes(//
					ResCodeMessage.CREATE_SETTING_FAILED.getCode(), //
					ResCodeMessage.CREATE_SETTING_FAILED.getMessage());
		}
	}

	// 刪除套餐
	@Transactional(rollbackFor = Exception.class)
	public BasicRes delSettingById(SettingDto dto) {

		// 確認套餐id是否 > 0
		if (dto.getSettingId() <= 0) {
			return new BasicRes(//
					ResCodeMessage.SETTING_ID_ERROR.getCode(), //
					ResCodeMessage.SETTING_ID_ERROR.getMessage());
		}

		// 確認套餐存在與否
		if (settingDao.checkSettingExist(dto.getSettingId()) == 0) {
			return new BasicRes(//
					ResCodeMessage.SETTING_NOT_FOUND.getCode(), //
					ResCodeMessage.SETTING_NOT_FOUND.getMessage());
		}

		SettingDto db = settingDao.getSettingById(dto.getSettingId());
		// 套餐啟用中無法刪除
		if (db.isSettingActive()) {
			return new BasicRes(//
					ResCodeMessage.SETTING_IS_USED.getCode(), //
					ResCodeMessage.SETTING_IS_USED.getMessage());
		}

		// 成功通過判斷後刪除套餐
		int result = settingDao.delSettingById(dto);
		if (result > 0) {
			return new BasicRes(//
					ResCodeMessage.SUCCESS.getCode(), //
					ResCodeMessage.SUCCESS.getMessage());
		} else {
			return new BasicRes(//
					ResCodeMessage.DELETE_SETTING_FAILED.getCode(), //
					ResCodeMessage.DELETE_SETTING_FAILED.getMessage());
		}
	}

	// 更新套餐
	@Transactional(rollbackFor = Exception.class)
	public BasicRes updateSetting(SettingBasicReq req) throws Exception {

		int settingId = req.getSettingId();

		// 套餐id不可以 < 0
		if (settingId <= 0) {
			return new BasicRes(//
					ResCodeMessage.SETTING_ID_ERROR.getCode(), //
					ResCodeMessage.SETTING_ID_ERROR.getMessage());
		}

		// 確認套餐存在與否
		if (settingDao.checkSettingExist(settingId) == 0) {
			return new BasicRes(//
					ResCodeMessage.SETTING_NOT_FOUND.getCode(), //
					ResCodeMessage.SETTING_NOT_FOUND.getMessage());
		}

		// 刪除舊的套餐
		SettingDto delDto = new SettingDto();
		delDto.setSettingId(settingId);
		;
		int delResult = settingDao.delSettingById(delDto);
		if (delResult <= 0) {
			return new BasicRes(//
					ResCodeMessage.DELETE_SETTING_FAILED.getCode(), //
					ResCodeMessage.DELETE_SETTING_FAILED.getMessage());
		}

		// 驗證新的套餐
		BasicRes addRes = this.addSetting(req);
		if (addRes.getCode() != ResCodeMessage.SUCCESS.getCode()) {
			throw new RuntimeException(addRes.getCode() + addRes.getMessage());
		}
		return addRes;
	}

	// 透過分類Id，查詢套餐列表(管理者)
	@Transactional(readOnly = true)
	public SettingListRes getSettingListById(int categoryId) throws Exception {

		// 分類id存在與否
		if (categoryDao.checkCategoryExistById(categoryId) == 0) {
			return new SettingListRes(//
					ResCodeMessage.CATEGORY_IS_NOT_FOUND.getCode(), //
					ResCodeMessage.CATEGORY_IS_NOT_FOUND.getMessage());
		}

		// 從資料庫拿 DTO 全部資料
		List<SettingDto> dtoList = settingDao.getSettingListById(categoryId);
		List<SettingVo> voList = new ArrayList<>();

		for (SettingDto dto : dtoList) {
			SettingVo vo = new SettingVo();
			// 存入基本屬性
			vo.setSettingId(dto.getSettingId());
			vo.setSettingName(dto.getSettingName());
			vo.setSettingPrice(dto.getSettingPrice());
			vo.setSettingImg(dto.getSettingImg());
			vo.setSettingActive(dto.isSettingActive());
			vo.setSettingNote(dto.getSettingNote());

			// JSON 轉 List<SettingDetailDto>
			if (dto.getSettingDetail() != null && !dto.getSettingDetail().isEmpty()) {
				List<SettingDetailDto> detailList = mapper.readValue( //
						dto.getSettingDetail(), new TypeReference<List<SettingDetailDto>>() {
						});
				vo.setSettingDetail(detailList);
			}
			voList.add(vo);
		}

		return new SettingListRes( //
				ResCodeMessage.SUCCESS.getCode(), //
				ResCodeMessage.SUCCESS.getMessage(), //
				categoryId, voList);
	}
	
	//透過分類id取得套餐(使用者)
	@Transactional(readOnly = true)
	public SettingListRes getUserSettingListById(int categoryId) throws Exception {

		// 分類id存在與否
		if (categoryDao.checkCategoryExistById(categoryId) == 0) {
			return new SettingListRes(//
					ResCodeMessage.CATEGORY_IS_NOT_FOUND.getCode(), //
					ResCodeMessage.CATEGORY_IS_NOT_FOUND.getMessage());
		}

		// 從資料庫拿 DTO 全部資料
		List<SettingDto> dtoList = settingDao.getUserSettingListById(categoryId);
		List<SettingVo> voList = new ArrayList<>();

		for (SettingDto dto : dtoList) {
			SettingVo vo = new SettingVo();
			// 存入基本屬性
			vo.setSettingId(dto.getSettingId());
			vo.setSettingName(dto.getSettingName());
			vo.setSettingPrice(dto.getSettingPrice());
			vo.setSettingImg(dto.getSettingImg());
			vo.setSettingActive(dto.isSettingActive());
			vo.setSettingNote(dto.getSettingNote());

			// JSON 轉 List<SettingDetailDto>
			if (dto.getSettingDetail() != null && !dto.getSettingDetail().isEmpty()) {
				List<SettingDetailDto> detailList = mapper.readValue( //
						dto.getSettingDetail(), new TypeReference<List<SettingDetailDto>>() {
						});
				vo.setSettingDetail(detailList);
			}
			voList.add(vo);
		}

		return new SettingListRes( //
				ResCodeMessage.SUCCESS.getCode(), //
				ResCodeMessage.SUCCESS.getMessage(), //
				categoryId, voList);
	}

	// 透過settingId查詢單筆套餐所有資訊(productId取得商品內容、categoryId取得客製化內容)
	// 使用者點餐用
	@Transactional(readOnly = true)
	public SettingAllDetailRes getSettingAllDetailById(int settingId) throws Exception {

		// 透過settingId取得整筆訂單基本資料
		SettingDto dto = settingDao.getSettingById(settingId);
		// 排除套餐不存在
		if (dto == null) {
			return new SettingAllDetailRes(ResCodeMessage.SETTING_NOT_FOUND.getCode(),
					ResCodeMessage.SETTING_NOT_FOUND.getMessage());
		}

		// 套餐分類id存在與否
		int categoryId = dto.getCategoryId();
		if (categoryDao.checkCategoryExistById(categoryId) == 0) {
			return new SettingAllDetailRes(//
					ResCodeMessage.CATEGORY_IS_NOT_FOUND.getCode(), //
					ResCodeMessage.CATEGORY_IS_NOT_FOUND.getMessage());
		}

		// 取得setting_detail字串
		String settingDetailJson = dto.getSettingDetail();
		// 字串轉json
		List<SettingDetailDto> settingDetailList = mapper.readValue(settingDetailJson,
				new TypeReference<List<SettingDetailDto>>() {});

		// 建立列表晚點填充所有需要的資料
		List<SettingCategoryDetailDto> finalList = new ArrayList<>();

		//對setting_detail跑回圈
		for (SettingDetailDto detail : settingDetailList) {
			
			//建立單筆settingDetail存放迴圈內的細節資訊
			SettingCategoryDetailDto settingDetail = new SettingCategoryDetailDto();

			//取得分類基本資料並填入
			CategoryDto category = categoryDao.getCategoryById(detail.getCategoryId());
			if (category != null) {
				settingDetail.setCategoryId(category.getCategoryId());
				settingDetail.setCategoryType(category.getCategoryType());
				settingDetail.setWorkstationId(category.getWorkstationId());
			}

			//建立products存放商品內容
			List<ProductVo> products = new ArrayList<>();
			//對detailList做迴圈取得商品資訊並填入
			for (SettingDetailProductDto detailList : detail.getDetailList()) {
				ProductVo product = productDao.getUserDetailByProductId(detail.getCategoryId() ,detailList.getProductId());
				if (product != null) {
					products.add(product);
				}
			}
			settingDetail.setDetailList(products);

			// 取得分類ID的客製化資料
			List<OptionVo> voList = optionDao.getOptionListByCategoryId(category.getCategoryId());
			// 建立optionList存放客製化內容
			List<OptionVo> optionList = new ArrayList<>();
			//對voList跑回圈放入客製化內容
			for (OptionVo optionDto : voList) {
				//建立vo存放客製化選項內容
				OptionVo vo = new OptionVo();
				// 基本資料加入
				vo.setOptionId(optionDto.getOptionId());
				vo.setOptionName(optionDto.getOptionName());
				vo.setMaxSelect(optionDto.getMaxSelect());

				//取得 OptionDetailDto 字串內容
				String jsonDetail = optionDto.getOptionDetailJson();
				if (StringUtils.hasText(jsonDetail)) {
					// 將 String 轉成 JSON 物件
					List<OptionDetailDto> detailList = mapper.readValue(jsonDetail,
							new TypeReference<List<OptionDetailDto>>() {});
					vo.setOptionDetail(detailList);
				}
				//將客製化單個選項加入客製化列表
				optionList.add(vo);
			}
			// 把 客製化 傳回 settingDetail 的 optionList
			settingDetail.setOptionList(optionList);
			// 把settingDetail放回finalList中做回傳
			finalList.add(settingDetail);
		}

		return new SettingAllDetailRes( //
				ResCodeMessage.SUCCESS.getCode(), //
				ResCodeMessage.SUCCESS.getMessage(), //
				categoryId, dto.getSettingId(), dto.getSettingName(), dto.getSettingPrice(), //
				dto.getSettingImg(), dto.isSettingActive(), dto.getSettingNote(), finalList);
	}

}
