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
import com.example.GroupProject.dto.OptionDetailDto;
import com.example.GroupProject.dto.OptionDto;
import com.example.GroupProject.request.OptionCreatReq;
import com.example.GroupProject.response.BasicRes;
import com.example.GroupProject.response.OptionListRes;
import com.example.GroupProject.vo.OptionVo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OptionService {

	// json跟java物件的轉換
	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private OptionDao optionDao;

	@Autowired
	private CategoryDao categoryDao;

	/** 新增客製化 */
	@Transactional(rollbackFor = Exception.class)
	public BasicRes addOption(OptionCreatReq req) throws Exception {

		// 名稱不可為空
		if (!StringUtils.hasText(req.getOptionName())) {
			return new BasicRes(//
					ResCodeMessage.OPTION_NAME_ERROR.getCode(), //
					ResCodeMessage.OPTION_NAME_ERROR.getMessage());
		}

		// 客製化名稱重複
		if (optionDao.checkOptionName(req.getOptionName())) {
			return new BasicRes( //
					ResCodeMessage.OPTION_NAME_IS_USED.getCode(), //
					ResCodeMessage.OPTION_NAME_IS_USED.getMessage());
		}

		// 客製化id不可為空
		if (req.getOptionId() <= 0) {
			return new BasicRes(//
					ResCodeMessage.OPTION_ID_ERROR.getCode(), //
					ResCodeMessage.OPTION_ID_ERROR.getMessage());
		}
		
		//客製化選項數量不可小於等於0
		if (req.getMaxSelect() <= 0) {
		    return new BasicRes(
		    		ResCodeMessage.MAXSELECT_ERROR.getCode(), //
					ResCodeMessage.MAXSELECT_ERROR.getMessage());
		}

		// 分類id存在與否
		if (categoryDao.checkCategoryExistById(req.getCategoryId()) == 0) {
			return new BasicRes(//
					ResCodeMessage.CATEGORY_IS_NOT_FOUND.getCode(), //
					ResCodeMessage.CATEGORY_IS_NOT_FOUND.getMessage());
		}

		// detail 為null，或空值
		if (req.getOptionDetail() == null || req.getOptionDetail().isEmpty()) {
			return new BasicRes(ResCodeMessage.OPTION_DETAIL_ERROR.getCode(),
					ResCodeMessage.OPTION_DETAIL_ERROR.getMessage());
		}

		Set<String> names = new HashSet<>();

		// detail 名稱為空、價格小於0、名稱已存在
		for (OptionDetailDto detail : req.getOptionDetail()) {
			if (!StringUtils.hasText(detail.getOption())) {
				return new BasicRes(ResCodeMessage.OPTION_DETAIL_NAME_EMPTY.getCode(),
						ResCodeMessage.OPTION_DETAIL_NAME_EMPTY.getMessage());
			}
			if (detail.getAddPrice() < 0) {
				return new BasicRes(ResCodeMessage.OPTION_DETAIL_PRICE_INVALID.getCode(),
						ResCodeMessage.OPTION_DETAIL_PRICE_INVALID.getMessage());
			}
			// 如果detail名稱已經存在 → 回傳 false
			if (!names.add(detail.getOption())) {
				return new BasicRes(ResCodeMessage.OPTION_DETAIL_DUPLICATE.getCode(),
						ResCodeMessage.OPTION_DETAIL_DUPLICATE.getMessage());
			}
		}

		// 創立新物件晚點存放dto
		OptionDto dto = new OptionDto();
		// 存入id跟名稱跟分類id
		dto.setOptionId(req.getOptionId());
		dto.setOptionName(req.getOptionName());
		dto.setCategoryId(req.getCategoryId());
		dto.setMaxSelect(req.getMaxSelect());

		// 字串存放轉換的detail
		String jsonString = mapper.writeValueAsString(req.getOptionDetail());
		dto.setOptionDetail(jsonString);

		// 成功通過判斷後新增客製化
		int result = optionDao.addOption(dto);
		if (result > 0) {
			return new BasicRes(//
					ResCodeMessage.SUCCESS.getCode(), //
					ResCodeMessage.SUCCESS.getMessage());
		} else {
			return new BasicRes(//
					ResCodeMessage.CREATE_OPTION_FAILED.getCode(), //
					ResCodeMessage.CREATE_OPTION_FAILED.getMessage());
		}
	}

	// 刪除客製化
	@Transactional(rollbackFor = Exception.class)
	public BasicRes delOptionById(OptionDto dto) {

		// 確認客製化id是否 > 0
		if (dto.getOptionId() <= 0) {
			return new BasicRes(//
					ResCodeMessage.OPTION_ID_ERROR.getCode(), //
					ResCodeMessage.OPTION_ID_ERROR.getMessage());
		}

		// 確認客製化存在與否
		if (optionDao.checkOptionExist(dto.getOptionId()) == 0) {
			return new BasicRes(ResCodeMessage.OPTION_NOT_FOUND.getCode(), //
					ResCodeMessage.OPTION_NOT_FOUND.getMessage());
		}

		// 成功通過判斷後刪除客製化
		int result = optionDao.delOptionById(dto);
		if (result > 0) {
			return new BasicRes(//
					ResCodeMessage.SUCCESS.getCode(), //
					ResCodeMessage.SUCCESS.getMessage());
		} else {
			return new BasicRes(//
					ResCodeMessage.DELETE_OPTION_FAILED.getCode(), //
					ResCodeMessage.DELETE_OPTION_FAILED.getMessage());
		}
	}

	// 更新客製化
	@Transactional(rollbackFor = Exception.class)
	public BasicRes updateOption(OptionCreatReq req) throws Exception {
		
		//客製化選項數量不可小於等於0
		if (req.getMaxSelect() <= 0) {
		    return new BasicRes(
		    		ResCodeMessage.MAXSELECT_ERROR.getCode(), //
					ResCodeMessage.MAXSELECT_ERROR.getMessage());
		}

		// 確認客製化id是否 > 0
		if (req.getOptionId() <= 0) {
			return new BasicRes(//
					ResCodeMessage.OPTION_ID_ERROR.getCode(), //
					ResCodeMessage.OPTION_ID_ERROR.getMessage());
		}

		// 確認客製化存在與否
		if (optionDao.checkOptionExist(req.getOptionId()) == 0) {
			return new BasicRes(ResCodeMessage.OPTION_NOT_FOUND.getCode(), //
					ResCodeMessage.OPTION_NOT_FOUND.getMessage());
		}

		// 刪除舊的客製化
		OptionDto delDto = new OptionDto();
		delDto.setOptionId(req.getOptionId());
		int delResult = optionDao.delOptionById(delDto);
		if (delResult <= 0) {
			return new BasicRes(//
					ResCodeMessage.DELETE_OPTION_FAILED.getCode(), //
					ResCodeMessage.DELETE_OPTION_FAILED.getMessage());
		}

		// 驗證新的客製化資料
		BasicRes addRes = this.addOption(req);
		if (addRes.getCode() != ResCodeMessage.SUCCESS.getCode()) {
			throw new RuntimeException(addRes.getCode() + addRes.getMessage());
		}
		return addRes;
	}

	// 查詢客製化列表(透過分類ID)
	@Transactional(readOnly = true)
	public OptionListRes getOptionList(int categoryId) throws Exception {

		// 分類id存在與否
		if (categoryDao.checkCategoryExistById(categoryId) == 0) {
			return new OptionListRes(//
					ResCodeMessage.CATEGORY_IS_NOT_FOUND.getCode(), //
					ResCodeMessage.CATEGORY_IS_NOT_FOUND.getMessage());
		}

		// 從資料庫拿 DTO 全部資料
		List<OptionDto> dtoList = optionDao.getOptionList(categoryId);

		// 建立voList 存放列表
		List<OptionVo> voList = new ArrayList<>();
		for (OptionDto dto : dtoList) {
			OptionVo vo = new OptionVo();
			// 基本資料加入
			vo.setOptionId(dto.getOptionId());
			vo.setOptionName(dto.getOptionName());
			vo.setMaxSelect(dto.getMaxSelect());

			// 把 JSON 字串轉成 List<OptionDetailDto>
			if (dto.getOptionDetail() != null && !dto.getOptionDetail().isEmpty()) {
				List<OptionDetailDto> detailList = mapper.readValue(dto.getOptionDetail(),
						new TypeReference<List<OptionDetailDto>>() {
						});
				vo.setOptionDetail(detailList);
			}
			// 客製化細節加入
			voList.add(vo);
		}
		return new OptionListRes(//
				ResCodeMessage.SUCCESS.getCode(), //
				ResCodeMessage.SUCCESS.getMessage(), categoryId, voList);
	}
}
