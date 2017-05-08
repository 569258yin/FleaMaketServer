package com.aygxy.fmaket.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.aygxy.fmaket.goods.entity.SearchTag;

@Repository
public interface SearchTagMapper {
	
	List<SearchTag> selectTop(@Param("num")int num);
	
	int delete(@Param("id") String id);
	
	int insert(SearchTag searchTag);
	
	SearchTag selectByTag(@Param("tag") String tag);
	
	int update(SearchTag searchTag);
	
}
