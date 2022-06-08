package com.example.demo.entity;

import java.sql.Date;

public class Manager {

    private int userId;
    private Date updatedDate;
    
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updated_date) {
		this.updatedDate = updated_date;
	}
    
	
    
}