package com.example.GroupProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.GroupProject.constants.ResCodeMessage;
import com.example.GroupProject.dao.PetListDao;
import com.example.GroupProject.response.BasicRes;
import com.example.GroupProject.response.PetListRes;
import com.example.GroupProject.vo.PetListVo;

@Service
public class PetListService {

	@Autowired
	private PetListDao petListDao;


	// create的部分
	public BasicRes addpetinfo(String name, int age, boolean catStatus, //
			String catImg, String catInfo) {

		if (!StringUtils.hasText(name)) {
			return new BasicRes(ResCodeMessage.PARAM_NAME_ERROR.getCode(), //
					ResCodeMessage.PARAM_NAME_ERROR.getMessage());
		}

		if (age < 0) {
			return new BasicRes(ResCodeMessage.PARAM_AGE_ERROR.getCode(), //
					ResCodeMessage.PARAM_AGE_ERROR.getMessage());
		}

		if (!StringUtils.hasText(catImg)) {
			return new BasicRes(ResCodeMessage.PARAM_IMG_ERROR.getCode(), //
					ResCodeMessage.PARAM_IMG_ERROR.getMessage());
		}

		if (!StringUtils.hasText(catInfo)) {
			return new BasicRes(ResCodeMessage.PARAM_INFO_ERROR.getCode(), //
					ResCodeMessage.PARAM_INFO_ERROR.getMessage());
		}

		try {
			petListDao.addpetinfo(name, age, catStatus, catImg, catInfo);
		} catch (Exception e) {
			throw e;
		}

		return new BasicRes(ResCodeMessage.SUCCESS.getCode(), //
				ResCodeMessage.SUCCESS.getMessage());

	};

	// update的部分
	public BasicRes updatepetinfo(int catId, String name, int age, boolean catStatus, String catImg, String catInfo) {

		if (!StringUtils.hasText(name)) {
			return new BasicRes(ResCodeMessage.PARAM_NAME_ERROR.getCode(), //
					ResCodeMessage.PARAM_NAME_ERROR.getMessage());
		}

		if (age < 0) {
			return new BasicRes(ResCodeMessage.PARAM_AGE_ERROR.getCode(), //
					ResCodeMessage.PARAM_AGE_ERROR.getMessage());
		}

		if (!StringUtils.hasText(catImg)) {
			return new BasicRes(ResCodeMessage.PARAM_IMG_ERROR.getCode(), //
					ResCodeMessage.PARAM_IMG_ERROR.getMessage());
		}

		if (!StringUtils.hasText(catInfo)) {
			return new BasicRes(ResCodeMessage.PARAM_INFO_ERROR.getCode(), //
					ResCodeMessage.PARAM_INFO_ERROR.getMessage());
		}

		try {
			int count = petListDao.getByCatId(catId);
			if (count == 0) {
				return new BasicRes(ResCodeMessage.PET_NOT_FOUND.getCode(), //
						ResCodeMessage.PET_NOT_FOUND.getMessage());
			}
			petListDao.updatepetinfo(catId, name, age, catStatus, catImg, catInfo);
		} catch (Exception e) {
			throw e;
		}

		return new BasicRes(ResCodeMessage.SUCCESS.getCode(), //
				ResCodeMessage.SUCCESS.getMessage());

	};

	// search的部分
	public PetListRes searchpetinfo(String name, Integer age, Boolean catStatus, String catInfo) {

		// 1. 調用 DAO 進行查詢
		try {
			// 假設 PetListDao 介面已定義了 List<PetList> searchpetinfo(...) 方法
			List<PetListVo> vo = petListDao.searchpetinfo(name, age, catStatus, catInfo);
			return new PetListRes(ResCodeMessage.SUCCESS.getCode(), //
					ResCodeMessage.SUCCESS.getMessage(), vo);
		} catch (Exception e) {
			// 處理資料庫異常，可能記錄日誌並拋出運行時異常
			System.out.println("查詢寵物資訊失敗" + e);
			return new PetListRes(ResCodeMessage.PARAM_ID_ERROR.getCode(), "刪除列表不能為空。");
		}
	};

	// delete的部分
	public BasicRes deleteByPetId(List<Integer> petIdList) throws Exception {

		if (petIdList == null || petIdList.isEmpty()) {
			return new BasicRes(ResCodeMessage.PARAM_ID_ERROR.getCode(), "刪除列表不能為空。");
		}

		try {
			// 刪
			petListDao.deleteByPetId(petIdList);
		} catch (Exception e) {
			throw e;
		}
		return new BasicRes(ResCodeMessage.SUCCESS.getCode(), //
				ResCodeMessage.SUCCESS.getMessage());
	};

}