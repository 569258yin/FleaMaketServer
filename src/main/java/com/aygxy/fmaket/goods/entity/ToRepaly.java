package com.aygxy.fmaket.goods.entity;

import java.util.Date;

public class ToRepaly {
    private String torepalyid;

    private String goodsreplayid;

    private String userid;

    private String torepalycontext;

    private Date torepalytime;

    public String getTorepalyid() {
        return torepalyid;
    }

    public void setTorepalyid(String torepalyid) {
        this.torepalyid = torepalyid == null ? null : torepalyid.trim();
    }

    public String getGoodsreplayid() {
        return goodsreplayid;
    }

    public void setGoodsreplayid(String goodsreplayid) {
        this.goodsreplayid = goodsreplayid == null ? null : goodsreplayid.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getTorepalycontext() {
        return torepalycontext;
    }

    public void setTorepalycontext(String torepalycontext) {
        this.torepalycontext = torepalycontext == null ? null : torepalycontext.trim();
    }

    public Date getTorepalytime() {
        return torepalytime;
    }

    public void setTorepalytime(Date torepalytime) {
        this.torepalytime = torepalytime;
    }
}