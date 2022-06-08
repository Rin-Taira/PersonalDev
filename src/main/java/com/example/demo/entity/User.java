package com.example.demo.entity;

public class User {
	
	private int id;
    private String loginId;
    private String name;
    private String password;
    private int paypayFlag;
    private String introduce;
    private int roleFlag;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getPaypayFlag() {
		return paypayFlag;
	}
	public void setPaypayFlag(int paypayFlag) {
		this.paypayFlag = paypayFlag;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public int getRoleFlag() {
		return roleFlag;
	}
	public void setRoleFlag(int roleFlag) {
		this.roleFlag = roleFlag;
	}

    
}
