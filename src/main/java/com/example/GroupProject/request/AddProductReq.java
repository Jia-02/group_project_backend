package com.example.GroupProject.request;

public class AddProductReq {

	private int workTableId;

	private String type;

	private String name;

	private String description;

	private int price;

	private String imageUrl;

	private boolean active;

	public int getWorkTableId() {
		return workTableId;
	}

	public void setWorkTableId(int workTableId) {
		this.workTableId = workTableId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
