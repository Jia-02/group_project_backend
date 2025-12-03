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
import com.example.GroupProject.dao.ProductDao;
import com.example.GroupProject.dao.SettingDao;
import com.example.GroupProject.dto.ProductDto;
import com.example.GroupProject.dto.SettingDetailDto;
import com.example.GroupProject.dto.SettingDetailProductDto;
import com.example.GroupProject.dto.SettingDto;
import com.example.GroupProject.request.SettingBasicReq;
import com.example.GroupProject.response.BasicRes;
import com.example.GroupProject.response.SettingListRes;
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

				// 商品id不可 <= 0
				if (productId <= 0) {
					return new BasicRes(ResCodeMessage.PRODUCT_ID_ERROR.getCode(),
							ResCodeMessage.PRODUCT_ID_ERROR.getMessage());
				}

				// 透過商品id呼叫商品資訊
				ProductDto productDto = productDao.getDetailByProductId(productId);

				// 檢查商品是否存在
				if (productDao.checkProductExist(productId) == 0) {
					return new BasicRes(ResCodeMessage.PRODUCT_NOT_FOUND.getCode(),
							ResCodeMessage.PRODUCT_NOT_FOUND.getMessage());
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

	// 透過分類Id，查詢套餐
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
						dto.getSettingDetail(),
						new TypeReference<List<SettingDetailDto>>(){});
				vo.setSettingDetail(detailList);
			}
			voList.add(vo);
		}

		return new SettingListRes( //
				ResCodeMessage.SUCCESS.getCode(), //
				ResCodeMessage.SUCCESS.getMessage(), //
				categoryId, 
				voList);
	}
}
