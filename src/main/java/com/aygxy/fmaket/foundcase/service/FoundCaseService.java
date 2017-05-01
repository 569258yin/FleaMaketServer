package com.aygxy.fmaket.foundcase.service;

import java.util.List;

import com.aygxy.fmaket.foundcase.entity.FoundCase;

public interface FoundCaseService {
	
	/**
	 * 保存
	 * @param foundCase
	 * @return
	 */
	public boolean saveFoundCase(FoundCase foundCase);
	
	
	/**
	 * 分页加载
	 * @param pageNum
	 * @param pageSize
	 * @param type
	 * @return
	 */
	public List<FoundCase> selectFoundCaseByType(int pageNum,int pageSize,int type);
	
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	public List<FoundCase> selectFoundCaseByUser(String userId);
	
	
	public boolean deleteFoundCaseById(String foundcaseId);
	
}
