package com.aygxy.fmaket.user.entity;

import java.util.Date;

public class PhoneCode {
	private String id;
	private String phone;
	private String code;
	private Date creatTime;
	
	public PhoneCode() {
	}
	
	public PhoneCode(String id, String phone, String code, Date creatTime) {
		super();
		this.id = id;
		this.phone = phone;
		this.code = code;
		this.creatTime = creatTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}
	
	
}
