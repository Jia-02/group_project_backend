package com.example.GroupProject.constants;

public enum ResCodeMessage {

	SUCCESS(200, "Success!!"), //
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
