package com.aygxy.fmaket.goods.entity;

import java.util.Date;

import com.aygxy.fmaket.user.entity.UserInfo;

public class Goods {
    private String goodsid;

    private String userid;
	
    private Integer goodstypeid;

    private String goodstitle;

    private Date goodstime;

    private String goodstext;

    private String goodsmoney;

    private String goodsoldmoney;

    private String goodsquality;

    private Integer goodsiconnumber;

    private String useraddress;

    private String userphone;

    private String coonecttime;

    private Integer goodslikenum;

    private Integer goodsrepalynum;

    private String goodsicon;
    
    private UserInfo userInfo;

    public String getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(String goodsid) {
        this.goodsid = goodsid == null ? null : goodsid.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public Integer getGoodstypeid() {
        return goodstypeid;
    }

    public void setGoodstypeid(Integer goodstypeid) {
        this.goodstypeid = goodstypeid;
    }

    public String getGoodstitle() {
        return goodstitle;
    }

    public void setGoodstitle(String goodstitle) {
        this.goodstitle = goodstitle == null ? null : goodstitle.trim();
    }

    public Date getGoodstime() {
        return goodstime;
    }

    public void setGoodstime(Date goodstime) {
        this.goodstime = goodstime;
    }

    public String getGoodstext() {
        return goodstext;
    }

    public void setGoodstext(String goodstext) {
        this.goodstext = goodstext == null ? null : goodstext.trim();
    }

    public String getGoodsmoney() {
        return goodsmoney;
    }

    public void setGoodsmoney(String goodsmoney) {
        this.goodsmoney = goodsmoney == null ? null : goodsmoney.trim();
    }

    public String getGoodsoldmoney() {
        return goodsoldmoney;
    }

    public void setGoodsoldmoney(String goodsoldmoney) {
        this.goodsoldmoney = goodsoldmoney == null ? null : goodsoldmoney.trim();
    }

    public String getGoodsquality() {
        return goodsquality;
    }

    public void setGoodsquality(String goodsquality) {
        this.goodsquality = goodsquality == null ? null : goodsquality.trim();
    }

    public Integer getGoodsiconnumber() {
        return goodsiconnumber;
    }

    public void setGoodsiconnumber(Integer goodsiconnumber) {
        this.goodsiconnumber = goodsiconnumber;
    }

    public String getUseraddress() {
        return useraddress;
    }

    public void setUseraddress(String useraddress) {
        this.useraddress = useraddress == null ? null : useraddress.trim();
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone == null ? null : userphone.trim();
    }

    public String getCoonecttime() {
        return coonecttime;
    }

    public void setCoonecttime(String coonecttime) {
        this.coonecttime = coonecttime == null ? null : coonecttime.trim();
    }

    public Integer getGoodslikenum() {
        return goodslikenum;
    }

    public void setGoodslikenum(Integer goodslikenum) {
        this.goodslikenum = goodslikenum;
    }

    public Integer getGoodsrepalynum() {
        return goodsrepalynum;
    }

    public void setGoodsrepalynum(Integer goodsrepalynum) {
        this.goodsrepalynum = goodsrepalynum;
    }

    public String getGoodsicon() {
        return goodsicon;
    }

    public void setGoodsicon(String goodsicon) {
        this.goodsicon = goodsicon == null ? null : goodsicon.trim();
    }

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

    
}