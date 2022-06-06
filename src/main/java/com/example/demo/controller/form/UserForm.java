package com.example.demo.controller.form;

import javax.validation.constraints.NotEmpty;

public class UserForm {

	@NotEmpty(message="IDは必須です")
	private String loginId;
	
    private String name;
    
	@NotEmpty(message="PASSは必須です")
    private String password;
    
	private int paypay_flag;
    private int role_flag;
    private String introduce;
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
	public int getPaypay_flag() {
		return paypay_flag;
	}
	public void setPaypay_flag(int paypay_flag) {
		this.paypay_flag = paypay_flag;
	}
	public int getRole_flag() {
		return role_flag;
	}
	public void setRole_flag(int role_flag) {
		this.role_flag = role_flag;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

    
}
