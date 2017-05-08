package com.aygxy.fmaket.goods.entity;

public class SearchTag {
	
	private String id;
	private String tag;
	private Integer count;
	
	public SearchTag() {
	}
	
	
	
	public SearchTag(String id, String tag, Integer count) {
		super();
		this.id = id;
		this.tag = tag;
		this.count = count;
	}



	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
	
}
