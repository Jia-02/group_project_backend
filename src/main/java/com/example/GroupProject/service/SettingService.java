package com.example.GroupProject.service;

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
import com.example.GroupProject.dto.SettingDetailDto;
import com.example.GroupProject.dto.SettingDetailProductDto;
import com.example.GroupProject.dto.SettingDto;
import com.example.GroupProject.request.SettingBasicReq;
import com.example.GroupProject.response.BasicRes;
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

		// 價格不可為空且不可為負 (假設價格為 Integer 或 int)
		if (req.getSettingPrice() <= 0) {
			return new BasicRes(ResCodeMessage.SETTING_PRICE_ERROR.getCode(),
					ResCodeMessage.SETTING_PRICE_ERROR.getMessage());
		}

		// 圖片路徑不可為空
		if (!StringUtils.hasText(req.getSettingImg())) {
			return new BasicRes(ResCodeMessage.SETTING_IMG_ERROR.getCode(),
					ResCodeMessage.SETTING_IMG_ERROR.getMessage());
		}

		// 主分類ID存在與否 (req.categoryId)
		if (categoryDao.checkCategoryExist(req.getCategoryId()) == 0) {
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
			Integer detailCategoryId = detailCategory.getCategoryId();

			// 分類id不可小0，null
			if (detailCategoryId == null || detailCategoryId <= 0) {
				return new BasicRes(ResCodeMessage.CATEGORY_ID_ERROR.getCode(), //
						ResCodeMessage.CATEGORY_ID_ERROR.getMessage());
			}

			// 分類id存在與否
			if (categoryDao.checkCategoryExist(req.getCategoryId()) == 0) {
				return new BasicRes(//
						ResCodeMessage.CATEGORY_IS_NOT_FOUND.getCode(), //
						ResCodeMessage.CATEGORY_IS_NOT_FOUND.getMessage());
			}

			// 套餐細節商品列表
			List<SettingDetailProductDto> productList = detailCategory.getDetailList();
			// ProductDto存在
			if (productList == null || productList.isEmpty()) {
				return new BasicRes(ResCodeMessage.DETAIL_PRODUCT_LIST_EMPTY.getCode(),
						ResCodeMessage.DETAIL_PRODUCT_LIST_EMPTY.getMessage());
			}

			// 同一分類中商品不可重複
			Set<Integer> productIds = new HashSet<>();
			// 遍歷內層 productList
			for (SettingDetailProductDto product : productList) {
				Integer productId = product.getProductId();

				// 商品id不可 <= 0，null
				if (productId == null || productId <= 0) {
					return new BasicRes(ResCodeMessage.PRODUCT_ID_ERROR.getCode(),
							ResCodeMessage.PRODUCT_ID_ERROR.getMessage());
				}

				// 檢查 product_id 是否重複
				if (!productIds.add(productId)) {
					return new BasicRes(ResCodeMessage.PRODUCT_DUPLICATE.getCode(),
							ResCodeMessage.PRODUCT_DUPLICATE.getMessage());
				}

				// 檢查商品存在
				if (productDao.checkProductExist(productId) == 0) {
					return new BasicRes(ResCodeMessage.PRODUCT_NOT_FOUND.getCode(),
							ResCodeMessage.PRODUCT_NOT_FOUND.getMessage());
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

}
