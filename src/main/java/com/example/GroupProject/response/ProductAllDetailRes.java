package com.example.GroupProject.response;

import java.util.List;

import com.example.GroupProject.vo.OptionVo;

public class ProductAllDetailRes extends BasicRes {

	private int categoryId;
	private int productId; // product_id (INT)
	private String productName; // product_name (VARCHAR(100))
	private int productPrice; // product_price (INT)
	private boolean productActive; // product_active (TINYINT)
	private String productDescription; // product_description (VARCHAR(300))
	private String imageUrl; // image_url (VARCHAR(300))
	private String productNote; // product_note (VARCHAR(300), NULL)

	// 商品所屬分類
	private String categoryType;
	private int workstationId;

	// 分類底下的所有客製化
	private List<OptionVo> optionList;

	public ProductAllDetailRes() {
		super();
	}

	public ProductAllDetailRes(int code, String message) {
		super(code, message);
	}

	public ProductAllDetailRes(int code, String message, int categoryId, int productId, String productName,
			int productPrice, boolean productActive, String productDescription, String imageUrl, String productNote,
			String categoryType, int workstationId, List<OptionVo> optionList) {
		super(code, message);
		this.categoryId = categoryId;
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productActive = productActive;
		this.productDescription = productDescription;
		this.imageUrl = imageUrl;
		this.productNote = productNote;
		this.categoryType = categoryType;
		this.workstationId = workstationId;
		this.optionList = optionList;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public boolean isProductActive() {
		return productActive;
	}

	public void setProductActive(boolean productActive) {
		this.productActive = productActive;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getProductNote() {
		return productNote;
	}

	public void setProductNote(String productNote) {
		this.productNote = productNote;
	}

	public String getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

	public int getWorkstationId() {
		return workstationId;
	}

	public void setWorkstationId(int workstationId) {
		this.workstationId = workstationId;
	}

	public List<OptionVo> getOptionList() {
		return optionList;
	}

	public void setOptionList(List<OptionVo> optionList) {
		this.optionList = optionList;
	}

}
