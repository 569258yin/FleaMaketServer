package com.aygxy.fmaket.foundcase.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.aygxy.fmaket.foundcase.dao.FoundCaseMapper;
import com.aygxy.fmaket.foundcase.entity.FoundCase;
@Service
public class FoundCaseServiceImpl implements FoundCaseService{
	
	@Resource
	FoundCaseMapper foundCaseMapper;
	
	@Override
	public boolean saveFoundCase(FoundCase foundCase) {
		int count = foundCaseMapper.insert(foundCase);
		if(count > 0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public List<FoundCase> selectFoundCaseByType(int pageNum, int pageSize, int type) {
		List<FoundCase> list = foundCaseMapper.selectAllByType(type, pageNum, pageSize);
		return list;
	}

	@Override
	public List<FoundCase> selectFoundCaseByUser(String userId) {
		List<FoundCase> list = foundCaseMapper.selectAllFromUser(userId);
		return list;
	}

	@Override
	public boolean deleteFoundCaseById(String foundcaseId) {
		int count = foundCaseMapper.deleteByPrimaryKey(foundcaseId);
		return count == 1 ? true : false;
	}

}
