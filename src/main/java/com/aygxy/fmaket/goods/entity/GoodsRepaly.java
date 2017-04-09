package com.aygxy.fmaket.goods.entity;

import java.util.Date;
import java.util.List;

import com.aygxy.fmaket.user.entity.UserInfo;

public class GoodsRepaly {
    private String goodsreplayid;
    
    private String goodsid;
    
    private String userid;
    
    private UserInfo userInfo;

    private Date goodsreplaytime;

    private String goodsreplaycontent;
    
    private List<ToRepaly> torepalyList;
    

    public String getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}

	public String getGoodsreplayid() {
        return goodsreplayid;
    }

    public void setGoodsreplayid(String goodsreplayid) {
        this.goodsreplayid = goodsreplayid == null ? null : goodsreplayid.trim();
    }

    public Date getGoodsreplaytime() {
        return goodsreplaytime;
    }

    public void setGoodsreplaytime(Date goodsreplaytime) {
        this.goodsreplaytime = goodsreplaytime;
    }

    public String getGoodsreplaycontent() {
        return goodsreplaycontent;
    }

    public void setGoodsreplaycontent(String goodsreplaycontent) {
        this.goodsreplaycontent = goodsreplaycontent == null ? null : goodsreplaycontent.trim();
    }

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public List<ToRepaly> getTorepalyList() {
		return torepalyList;
	}

	public void setTorepalyList(List<ToRepaly> torepalyList) {
		this.torepalyList = torepalyList;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	
    
}