package com.example.GroupProject.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
public class Account {

	private int id;
	
	@NotBlank(message = "帳號不能為空")
    @Size(min = 3, max = 20, message = "帳號長度需 3–20 字元")
	private String userName;
	
	@NotBlank(message = "密碼不能為空")
    @Size(min = 4, message = "密碼至少 4 位")
	private String password;
	
	private String name;
	
	@NotBlank(message = "電話不能為空")
    @Pattern(regexp = "^09\\d{8}$", message = "電話號碼格式不正確（需為 09 開頭共 10 碼）")
	private String phone;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
