package com.aygxy.fmaket.goods.entity;

public class GoodsSearch {
	
	private String searchId;
	private String goodsId;
	private String keyWords;
	
	public GoodsSearch() {
	}
	
	
	
	public GoodsSearch(String searchId, String goodsId, String keyWords) {
		super();
		this.searchId = searchId;
		this.goodsId = goodsId;
		this.keyWords = keyWords;
	}



	public String getSearchId() {
		return searchId;
	}
	public void setSearchId(String searchId) {
		this.searchId = searchId;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public String getKeyWords() {
		return keyWords;
	}
	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}



	@Override
	public String toString() {
		return "GoodsSearch [goodsId=" + goodsId + ", keyWords=" + keyWords + "]";
	}
	
	
	
}
