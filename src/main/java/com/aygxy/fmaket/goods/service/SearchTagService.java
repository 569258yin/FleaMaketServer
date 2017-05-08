package com.aygxy.fmaket.goods.service;

import java.util.List;

import com.aygxy.fmaket.goods.entity.SearchTag;

public interface SearchTagService {

	public List<SearchTag> selectTop(int num);
	
	public boolean insertOrUpdate(SearchTag searchTag);
}
