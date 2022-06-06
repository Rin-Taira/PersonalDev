package com.example.demo.controller.form;

import java.sql.Timestamp;

public class OrderForm {

    private String menuName;
    private String userName;
    private int price;
    private int brown_flag;
    private int big_flag;
    private int rice_inc_flag;
    private Timestamp orderDate;
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getBrown_flag() {
		return brown_flag;
	}
	public void setBrown_flag(int brown_flag) {
		this.brown_flag = brown_flag;
	}
	public int getBig_flag() {
		return big_flag;
	}
	public void setBig_flag(int big_flag) {
		this.big_flag = big_flag;
	}
	public int getRice_inc_flag() {
		return rice_inc_flag;
	}
	public void setRice_inc_flag(int rice_inc_flag) {
		this.rice_inc_flag = rice_inc_flag;
	}
	public Timestamp getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}
    
	
    
}