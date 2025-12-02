package com.example.GroupProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.example.GroupProject.constants.ResCodeMessage;
import com.example.GroupProject.dao.CategoryDao;
import com.example.GroupProject.dto.CategoryDto;
import com.example.GroupProject.response.BasicRes;
import com.example.GroupProject.response.CategoryListRes;

@Service
public class CategoryService {

	@Autowired
	private CategoryDao categoryDao;


	/** 私有共同判斷輸入值 */
	private BasicRes validateCategory(CategoryDto dto) {

		// 餐點分類不可 null，且至少非一個空白字元
		if (!StringUtils.hasText(dto.getCategoryType())) {
			return new BasicRes(ResCodeMessage.CATEGORY_TYPE_ERROR.getCode(),
					ResCodeMessage.CATEGORY_TYPE_ERROR.getMessage());
		}
		// 工作台 id 必須大於 0
		if (dto.getWorkstationId() <= 0) {
			return new BasicRes(ResCodeMessage.WORKSTATION_ID_ERROR.getCode(),
					ResCodeMessage.WORKSTATION_ID_ERROR.getMessage());
		}
		//判斷工作台是否存在 - 等待創完工作台解鎖
//		if (dto.getWorkstationId() != null) {
//		    int exist = workstationDao.checkWorkstationExist(dto.getWorkstationId());
//		    if (exist == 0) {
//		        return new BasicRes(
//		            ResCodeMessage.WORKSTATION_NOT_FOUND.getCode(),
//		            ResCodeMessage.WORKSTATION_NOT_FOUND.getMessage()
//		        );
//		    }
//		}
		return null;
	}

	/** 新增分類 */
	@Transactional(rollbackFor = Exception.class)
	public BasicRes addCategory(CategoryDto dto) {

		BasicRes validateRes = validateCategory(dto);
		if (validateRes != null) {
			return validateRes;
		}
		
		// 重複名稱之分類
		if (categoryDao.checkCategoryName(dto.getCategoryType())) {
			return new BasicRes(ResCodeMessage.CATEGORY_ALREADY_EXISTS.getCode(),
					ResCodeMessage.CATEGORY_ALREADY_EXISTS.getMessage());
		}

		// 成功通過判斷後新增分類
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

		// 分類ID不存在
		if (categoryDao.checkCategoryExistById(dto.getCategoryId()) == 0) {
			return new BasicRes(//
					ResCodeMessage.CATEGORY_IS_NOT_FOUND.getCode(), //
					ResCodeMessage.CATEGORY_IS_NOT_FOUND.getMessage());
		}

		// 於商品中使用的分類不可刪除
		if (categoryDao.checkProductCategoryUsed(dto.getCategoryId()) > 0) {
			return new BasicRes(//
					ResCodeMessage.PRODUCT_IS_USED.getCode(), //
					ResCodeMessage.PRODUCT_IS_USED.getMessage());
		}
		
		//客製化使用中的分類不可刪除
		if (categoryDao.checkOptionCategoryUsed(dto.getCategoryId()) > 0) {
			return new BasicRes(//
					ResCodeMessage.OPTION_IS_USED.getCode(), //
					ResCodeMessage.OPTION_IS_USED.getMessage());
		}
		
		// 成功通過判斷後刪除
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

	/** 更新分類 */
	@Transactional(rollbackFor = Exception.class)
	public BasicRes updateCategory(CategoryDto dto) {

		// 分類ID不存在
		if (categoryDao.checkCategoryExistById(dto.getCategoryId()) == 0) {
			return new BasicRes(//
					ResCodeMessage.CATEGORY_IS_NOT_FOUND.getCode(), //
					ResCodeMessage.CATEGORY_IS_NOT_FOUND.getMessage());
		}

		BasicRes validateRes = validateCategory(dto);
		if (validateRes != null) {
			return validateRes;
		}

		int result = categoryDao.updateCategory(dto);
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
	
	/** 查看分類列表 */
	@Transactional(readOnly = true)
	public CategoryListRes getCategoryList() {
		return new CategoryListRes(//
				ResCodeMessage.SUCCESS.getCode(), //
				ResCodeMessage.SUCCESS.getMessage(), //
				categoryDao.getCategoryList());
	}

}
