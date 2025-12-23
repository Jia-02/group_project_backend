package com.example.GroupProject.response;

public class DeliveryUserRes extends BasicRes {

	private Integer id;
	
	private String name;
	
	private String phone;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public DeliveryUserRes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DeliveryUserRes(int code, String message) {
		super(code, message);
		// TODO Auto-generated constructor stub
	}

	public DeliveryUserRes(int code, String message, Integer id, String name, String phone) {
		super(code, message);
		this.id = id;
		this.name = name;
		this.phone = phone;
	}

	
}
