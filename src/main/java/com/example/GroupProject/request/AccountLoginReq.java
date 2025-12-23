package com.example.GroupProject.request;

import jakarta.validation.constraints.NotBlank;

public class AccountLoginReq {
	@NotBlank(message = "帳號不能為空")
	private String userName;

	@NotBlank(message = "密碼不能為空")
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
