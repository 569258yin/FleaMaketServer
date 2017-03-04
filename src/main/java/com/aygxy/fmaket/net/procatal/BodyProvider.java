package com.aygxy.fmaket.net.procatal;

import com.aygxy.fmaket.param.OelementType;
/**
 * 产生特定的body对象
 * @author YH
 *
 */
public class BodyProvider {
	
	public static Body getSuccessBody(){
		return getSuccessBody("");
	}
	
	public static Body getSuccessBody(String message){
		Body body = new Body();
		body.setOelement(new Oelement(OelementType.SUCCESS, message));
		return body;
	}
	
	public static Body getFaildBody(){
		return getSuccessBody("");
	}
	
	public static Body getFaildBody(String message){
		Body body = new Body();
		body.setOelement(new Oelement(OelementType.FAILD, message));
		return body;
	}
	
	
	
}
