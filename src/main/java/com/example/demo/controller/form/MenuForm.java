package com.example.demo.controller.form;

public class MenuForm {

	private int id;
	private String menuName;
	private int price;
	private int categoryId;
	private String categoryName;
	private String description;
	private int reviewAmount;
	private int reviewStarAmount;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getReviewAmount() {
		return reviewAmount;
	}
	public void setReviewAmount(int reviewAmount) {
		this.reviewAmount = reviewAmount;
	}
	public int getReviewStarAmount() {
		return reviewStarAmount;
	}
	public void setReviewStarAmount(int reviewStarAmount) {
		this.reviewStarAmount = reviewStarAmount;
	}

}