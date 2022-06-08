package com.example.demo.entity;

import java.sql.Timestamp;

public class Review {

    private int menuId;
    private String menuName;
    private int userId;
    private String userName;
    private int star;
    private String review;
    private Timestamp reviewDate;
    
    public Review() {
    }
    
    public Review(int menuId, String menuName, int userId, String userName, int star, String review) {
    	this.menuId = menuId;
    	this.menuName = menuName;
    	this.userId = userId;
    	this.userName = userName;
    	this.star = star;
    	this.review = review;
    }
    
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public Timestamp getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(Timestamp reviewDate) {
		this.reviewDate = reviewDate;
	}

}