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
	TABLE_ID_EXIST(400,"桌位ID已存在"),//
	TABLE_POSITION_EXIST(400,"桌位位置已存在其他桌位"),//
	
	ADD_INFO_FAILED(400, "Add info failed!!"), //
	UPDATE_INOF_FAILED(400, "Update info failed!!"), //
	NOT_FOUND(404, "Not found!!"), //
	PARAM_ACCOUNT_ERROR(400, "Param account error!!"), //
	PARAM_PASSWORD_ERROR(400, "Param password error!!"), //
	PASSWORD_MISMATCH(400, "Password mismatch!!"), //
	PARAM_ACCOUNT_EXIST(400, "Account already exist!!"),//
	LOGIN_FAILED(400, "Login failed!!"),//
	QUESTION_TYPE_ERROR(400,ConstantsMessage.QUESTION_TYPE_ERROR),//
	QUESTION_TYPE_OPTIONS_MISMATCH(400, "Question type and options mismatch!!"),//
	QUIZ_DATE_ERROR(400,"Quiz date error!!");

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
