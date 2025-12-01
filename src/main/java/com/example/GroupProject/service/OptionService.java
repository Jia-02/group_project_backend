package com.example.GroupProject.service;

import java.util.HashSet;
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
		
		//名稱不可為空
		if(!StringUtils.hasText(req.getOptionName())) {
			return new BasicRes(//
					ResCodeMessage.OPTION_NAME_ERROR.getCode(), //
					ResCodeMessage.OPTION_NAME_ERROR.getMessage());
		}
		
		//客製化id不可為空
		if (req.getOptionId() <= 0) {
			return new BasicRes(//
					ResCodeMessage.OPTION_ID_ERROR.getCode(), //
					ResCodeMessage.OPTION_ID_ERROR.getMessage());
		}
		
		//分類id存在與否
		if (categoryDao.checkCategoryExist(req.getCategoryId()) == 0) {
			return new BasicRes(//
					ResCodeMessage.CATEGORY_IS_NOT_FOUND.getCode(), //
					ResCodeMessage.CATEGORY_IS_NOT_FOUND.getMessage());
		}
		
		//detail 為null，或空值
		if (req.getOptionDetail() == null || req.getOptionDetail().isEmpty()) {
		    return new BasicRes(ResCodeMessage.OPTION_DETAIL_ERROR.getCode(),
		                        ResCodeMessage.OPTION_DETAIL_ERROR.getMessage());
		}
		
		//detail 名稱為空、價格小於0、名稱已存在
		for (OptionDetailDto detail : req.getOptionDetail()) {
		    if (!StringUtils.hasText(detail.getOption())) {
		        return new BasicRes(ResCodeMessage.OPTION_DETAIL_NAME_EMPTY.getCode(),
		                            ResCodeMessage.OPTION_DETAIL_NAME_EMPTY.getMessage());
		    }
		    if (detail.getAddPrice() < 0) {
		        return new BasicRes(ResCodeMessage.OPTION_DETAIL_PRICE_INVALID.getCode(),
		                            ResCodeMessage.OPTION_DETAIL_PRICE_INVALID.getMessage());
		    }
		    //如果名稱已經存在 → 回傳 false
		    Set<String> names = new HashSet<>();
		    if (!names.add(detail.getOption())) {
		        return new BasicRes(ResCodeMessage.OPTION_DETAIL_DUPLICATE.getCode(),
		                            ResCodeMessage.OPTION_DETAIL_DUPLICATE.getMessage());
		    }
		}
		
		//創立新物件晚點存放dto
		OptionDto dto = new OptionDto();
		//存入id跟名稱跟分類id
        dto.setOptionId(req.getOptionId());
        dto.setOptionName(req.getOptionName());
        dto.setCategoryId(req.getCategoryId());
        
        //字串存放轉換的detail
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
}
