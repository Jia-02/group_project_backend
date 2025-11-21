package com.example.GroupProject.dto;

public class ProductDto {
	
	private int productId;         // product_id (INT)
    private String productName;        // product_name (VARCHAR(100))
    private int productPrice;      // product_price (INT)
    private boolean productActive;        // product_active (TINYINT)
    private String productDescription; // product_description (VARCHAR(300))
    private String imageUrl;           // image_url (VARCHAR(300))
    private String productNote;        // product_note (VARCHAR(300), NULL)
    private int categoryId;        // category_id (INT)
    
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
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

}
