package com.example.GroupProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.example.GroupProject.constants.ResCodeMessage;
import com.example.GroupProject.dao.CategoryDao;
import com.example.GroupProject.dto.CategoryDto;
import com.example.GroupProject.response.BasicRes;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryDao categoryDao;
	
	/** 新增分類 */
	@Transactional(rollbackFor = Exception.class)
	public BasicRes addCategory(CategoryDto dto) {
		
		//餐點分類不可null，且至少非一個空白字元
		if(!StringUtils.hasText(dto.getCategoryType())) {
			return new BasicRes(//
					ResCodeMessage.CATEGORY_TYPE_ERROR.getCode(), //
					ResCodeMessage.CATEGORY_TYPE_ERROR.getMessage());
		}
		
		//工作台id小於0
		if(dto.getWorkstationId() <= 0) {
			return new BasicRes(//
					ResCodeMessage.WORKSTATION_ID_ERROR.getCode(), //
					ResCodeMessage.WORKSTATION_ID_ERROR.getMessage());
		}
		
		//不可重複輸入同名稱之分類
	     if (categoryDao.checkCategoryExists(dto.getCategoryType(), dto.getWorkstationId())) {
	         return new BasicRes(
						ResCodeMessage.CATEGORY_ALREADY_EXISTS.getCode(), //
						ResCodeMessage.CATEGORY_ALREADY_EXISTS.getMessage());
	     }
		
		//成功通過判斷後新增分類
		int result = categoryDao.addCategory(dto);
		if (result > 0) {
			return new BasicRes(//
					ResCodeMessage.SUCCESS.getCode(), //
					ResCodeMessage.SUCCESS.getMessage());
		} else {
			return new BasicRes(//
					ResCodeMessage.CREATE_CATEGORY_FAILED.getCode(), //
					ResCodeMessage.CREATE_CATEGORY_FAILED.getMessage());
		}
	}
	
	/** 刪除分類 */
	@Transactional(rollbackFor = Exception.class)
	public BasicRes delCategoryById(CategoryDto dto) {
		//成功通過判斷後新增分類
		int result = categoryDao.delCategoryById(dto);
		if (result > 0) {
			return new BasicRes(//
					ResCodeMessage.SUCCESS.getCode(), //
					ResCodeMessage.SUCCESS.getMessage());
		} else {
			return new BasicRes(//
					ResCodeMessage.CREATE_CATEGORY_FAILED.getCode(), //
					ResCodeMessage.CREATE_CATEGORY_FAILED.getMessage());
		}
	}
	
}
