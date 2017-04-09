package com.aygxy.fmaket.net.procatal;

import java.io.Serializable;

public class Oelement implements Serializable{
	private int code;
	private String message;
	
	public Oelement() {
	}
	
	public Oelement(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
