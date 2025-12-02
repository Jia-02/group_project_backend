package com.example.GroupProject.constants;

public enum ResCodeMessage {

	SUCCESS(200, "成功!!!"), //
	
	//訂位錯誤相關
	RESERVATION_NOT_FOUND(400, "找不到訂位。"), //
	CREATE_RESERVATION_FAILED(400, "訂位失敗。"), //
	PEOPLE_COUNT_FAILED(400, "人數輸入錯誤。"), //
	PHONE_IS_RESERVATION_IN_DATE(400, "已於今日預約過。"), //
	DELETE_RESERVATION_FAILED(400, "預約刪除失敗。"), //
	UPDATE_RESERVATION_FAILED(400, "預約更新失敗。"), //
	CHILD_SEAT_INSUFFICIENT(400, "兒童座椅數量不足。"), //
	
	//桌位錯誤
	TABLE_NOT_FOUND(400, "無法找到桌位。"), //
	TABLE_IS_RESERVATION(400, "桌位已被訂位"), //
	TABLE_IS_NOT_OPEN(400, "桌位未開放"), //
	TABLE_CAPACITY_INSUFFICIENT(400, "桌位容納不下這個人數"), //
	TABLE_CAPACITY_ERROR(400, "桌位可容納人數錯誤"), //
	TABLE_ID_EXIST(400,"桌位ID已存在"),//
	TABLE_POSITION_EXIST(400,"桌位位置已存在其他桌位"),//
	TABLE_POSITION_ERROR(400,"桌位超出範圍"),//
	TABLE_WIDTH_ERROR(400,"桌位寬度錯誤"),//
	TABLE_HEIGHT_ERROR(400,"桌位長度錯誤"),//
	
	//分類錯誤CATEGORY
	CREATE_CATEGORY_FAILED(400, "新增菜單分類失敗。"), //
	CATEGORY_TYPE_ERROR(400, "菜單分類名稱錯誤。"), //
	CATEGORY_ALREADY_EXISTS(400, "菜單分類已存在。"), //
	CATEGORY_IS_NOT_FOUND(400, "菜單分類ID找不到。"), //
	CATEGORY_ID_ERROR(400, "菜單ID錯誤。"), //
	
	//工作台錯誤WORKSTATION
	WORKSTATION_ID_ERROR(400, "工作台ID不可小於0"), //
	WORKSTATION_NOT_FOUND(400, "工作台不存在"), //
	WORKSTATION_NAME_ERROR(400, "工作台名稱錯誤"), //
	
	//餐點錯誤
	PRODUCT_NAME_ERROR(400, "餐點名稱錯誤"), //
	PRODUCT_NAME_IS_USED(400, "餐點名稱重複"), //
	PRODUCT_ID_ERROR(400, "餐點ID錯誤"), //
	PRODUCT_PRICE_ERROR(400, "餐點價格錯誤"), //
	CREATE_PRODUCT_FAILED(400, "新增餐點失敗。"), //
	DELETE_PRODUCT_FAILED(400, "刪除餐點失敗。"), //
	PRODUCT_NOT_FOUND(400, "找不到餐點。"), //
	PRODUCT_IS_USED(400, "商品上架中。"), //
	UPDATE_PRODUCT_FAILED(400, "餐點更新失敗。"), //
	PRODUCT_DUPLICATE(400, "餐點重覆。"), //
	PRODUCT_AND_CATEGORY_NOT_MATCH(400, "餐點與分類不匹配。"), //
	
	//客製化
	OPTION_ID_ERROR(400, "客製化ID錯誤"), //
	OPTION_NAME_IS_USED(400, "客製化名稱重複"), //
	OPTION_DETAIL_ERROR(400, "客製化細節錯誤"), //
	OPTION_NAME_ERROR(400, "客製化名稱錯誤"), //
	MAXSELECT_ERROR(400, "客製化最大選項數量錯誤。"), //
	OPTION_DETAIL_NAME_EMPTY(400, "客製化細節名稱為空"), //
	OPTION_DETAIL_PRICE_INVALID(400, "客製化價格小於0"), //
	OPTION_DETAIL_DUPLICATE(400, "客製化細節名稱重複"), //
	OPTION_NOT_FOUND(400, "找不到客製化。"), //
	OPTION_IS_USED(400, "客製化使用中。"), //
	CREATE_OPTION_FAILED(400, "新增客製化失敗。"), //
	DELETE_OPTION_FAILED(400, "客製化刪除失敗。"), //
	
	//套餐
	SETTING_ID_ERROR(400, "套餐ID錯誤。"), //
	SETTING_NAME_ERROR(400, "套餐名字錯誤。"), //
	SETTING_PRICE_ERROR(400, "套餐價格錯誤。"), //
	SETTING_IMG_ERROR(400, "套餐圖片錯誤。"), //
	SETTING_ACTIVE_ERROR(400, "套餐狀態錯誤。"), //
	SETTING_DETAIL_EMPTY(400, "套餐細節為空錯誤。"), //
	DETAIL_PRODUCT_LIST_EMPTY(400, "套餐細節中的商品列表為空錯誤。"), //
	SETTING_NAME_IS_USED(400, "套餐名稱重複。"), //
	SETTING_NOT_FOUND(400, "找不到套餐。"), //
	SETTING_IS_USED(400, "套餐開放使用中。"), //
	CREATE_SETTING_FAILED(400, "建立套餐失敗。"), //
	DELETE_SETTING_FAILED(400, "套餐刪除失敗。"), //
	
	ADD_INFO_FAILED(400, "Add info failed!!"), //
	UPDATE_INOF_FAILED(400, "Update info failed!!"), //
	NOT_FOUND(404, "Not found!!"), //
	PARAM_ACCOUNT_ERROR(400, "Param account error!!"), //
	PARAM_PASSWORD_ERROR(400, "Param password error!!"), //
	PASSWORD_MISMATCH(400, "Password mismatch!!"), //
	PARAM_ACCOUNT_EXIST(400, "Account already exist!!"),//
	LOGIN_FAILED(400, "Login failed!!");//


	private int code;

	private String message;

	private ResCodeMessage(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
