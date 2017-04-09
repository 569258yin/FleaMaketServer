package com.aygxy.fmaket.goods.entity;

import java.util.Date;

import com.aygxy.fmaket.user.entity.UserInfo;

public class ToRepaly {
    private String torepalyid;

    private String goodsreplayid;
    
    private String userId;
    
    private String touserId;
    
    private UserInfo userinfo;
    
    private UserInfo toUserinfo;

    private String torepalycontext;

    private Date torepalytime;

	public String getTorepalyid() {
		return torepalyid;
	}

	public void setTorepalyid(String torepalyid) {
		this.torepalyid = torepalyid;
	}

	public String getGoodsreplayid() {
		return goodsreplayid;
	}

	public void setGoodsreplayid(String goodsreplayid) {
		this.goodsreplayid = goodsreplayid;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTouserId() {
		return touserId;
	}

	public void setTouserId(String touserId) {
		this.touserId = touserId;
	}

	public UserInfo getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(UserInfo userinfo) {
		this.userinfo = userinfo;
	}

	public UserInfo getToUserinfo() {
		return toUserinfo;
	}

	public void setToUserinfo(UserInfo toUserinfo) {
		this.toUserinfo = toUserinfo;
	}

	public String getTorepalycontext() {
		return torepalycontext;
	}

	public void setTorepalycontext(String torepalycontext) {
		this.torepalycontext = torepalycontext;
	}

	public Date getTorepalytime() {
		return torepalytime;
	}

	public void setTorepalytime(Date torepalytime) {
		this.torepalytime = torepalytime;
	}

   
    
}