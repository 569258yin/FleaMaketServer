package com.aygxy.fmaket.goods.entity;

public class GoodsLike {
    private String goodslikeid;

    private String goodsid;

    private String userid;

    public String getGoodslikeid() {
        return goodslikeid;
    }

    public void setGoodslikeid(String goodslikeid) {
        this.goodslikeid = goodslikeid == null ? null : goodslikeid.trim();
    }

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
}