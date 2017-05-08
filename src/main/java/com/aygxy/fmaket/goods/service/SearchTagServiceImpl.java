package com.aygxy.fmaket.goods.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.aygxy.fmaket.goods.dao.SearchTagMapper;
import com.aygxy.fmaket.goods.entity.SearchTag;

@Service("searchTagService")
public class SearchTagServiceImpl implements SearchTagService {
	
	@Resource
	SearchTagMapper searchTagMapper;
	
	@Override
	public List<SearchTag> selectTop(int num) {
		List<SearchTag> lists = searchTagMapper.selectTop(10);
		return lists;
	}

	@Override
	public boolean insertOrUpdate(SearchTag searchTag) {
		SearchTag temp = searchTagMapper.selectByTag(searchTag.getTag());
		boolean result = false;
		if(temp != null){
			temp.setCount(temp.getCount()+1);
			result = searchTagMapper.update(temp) == 1;
		}else{
			result = searchTagMapper.insert(searchTag) == 1;
		}
		return result;
	}

}
