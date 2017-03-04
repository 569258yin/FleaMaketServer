package com.aygxy.fmaket.goods.entity;

import java.util.Date;

public class GoodsRepaly {
    private String goodsreplayid;

    private String userid;

    private Date goodsreplaytime;

    private String goodsreplaycontent;

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
}