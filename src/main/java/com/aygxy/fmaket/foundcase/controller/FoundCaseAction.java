package com.aygxy.fmaket.foundcase.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aygxy.fmaket.debug.DebugLog;
import com.aygxy.fmaket.foundcase.entity.FoundCase;
import com.aygxy.fmaket.foundcase.service.FoundCaseService;
import com.aygxy.fmaket.goods.entity.Goods;
import com.aygxy.fmaket.net.jsonbean.PageJsonData;
import com.aygxy.fmaket.net.procatal.Body;
import com.aygxy.fmaket.net.procatal.BodyProvider;
import com.aygxy.fmaket.param.GlobalParams;
import com.aygxy.fmaket.util.GsonUtil;

@Controller
@RequestMapping("/foundCase")
public class FoundCaseAction {
	
	@Resource
	FoundCaseService foundCaseService;
	
	@RequestMapping("/sendFoundCase.action")
	@ResponseBody
	public void sendFoundCase(HttpServletRequest request){
		Body resultbody =  BodyProvider.getFaildBody("发布失败");
		try {
			String body = (String) request.getAttribute("body");
			FoundCase foundCase = GsonUtil.stringToObjectByBean(body, FoundCase.class);
			if(foundCase != null){
				foundCase.setFdcid(UUID.randomUUID().toString().replace("-", "")+foundCase.hashCode());
				foundCase.setFdctime(new Date());
				foundCase.setModifyTime(foundCase.getFdctime());
				boolean result = foundCaseService.saveFoundCase(foundCase);
				if(result){
					resultbody = BodyProvider.getSuccessBody();
				}
			}
		} catch (Exception e) {
			DebugLog.logger.error("发布失物招领失败", e);
		}
		request.getSession().setAttribute(GlobalParams.RESULT,resultbody);
	}
	
	@RequestMapping("/getFoundCaseByType.action")
	@ResponseBody
	public void getFoundCaseByType(HttpServletRequest request){
		Body resultbody =  BodyProvider.getFaildBody("获取失物招领失败");
		try {
			String body = (String) request.getAttribute("body");
			PageJsonData pageJsonData = GsonUtil.stringToObjectByBean(body, PageJsonData.class);
			if(pageJsonData != null){
				List<FoundCase> lists = foundCaseService.selectFoundCaseByType(pageJsonData.getPageNum(), pageJsonData.getPageSize(),pageJsonData.getType());
				String json = GsonUtil.objectToString(lists);
				resultbody = BodyProvider.getSuccessBody();
				resultbody.setElements(json);
			}
		} catch (Exception e) {
			DebugLog.logger.error("获取失物招领失败", e);
		}
		request.getSession().setAttribute(GlobalParams.RESULT,resultbody);
	}
	
	@RequestMapping("/getFoundCaseByUserId.action")
	@ResponseBody
	public void getFoundCaseByUserId(HttpServletRequest request){
		Body resultbody =  BodyProvider.getFaildBody("获取失物招领失败");
		try {
			String body = (String) request.getAttribute("body");
			PageJsonData pageJsonData = GsonUtil.stringToObjectByBean(body, PageJsonData.class);
			if(pageJsonData != null){
				List<FoundCase> lists = foundCaseService.selectFoundCaseByUser(pageJsonData.getId());
				String json = GsonUtil.objectToString(lists);
				resultbody = BodyProvider.getSuccessBody();
				resultbody.setElements(json);
			}
		} catch (Exception e) {
			DebugLog.logger.error("获取失物招领失败", e);
		}
		request.getSession().setAttribute(GlobalParams.RESULT,resultbody);
	}
	
	@RequestMapping("/deleteFoundcaseById.action")
	@ResponseBody
	public void deleteFoundcaseById(HttpServletRequest request) {
		Body resultbody =  BodyProvider.getFaildBody("删除失败");
		try {
			String body = (String) request.getAttribute("body");
			PageJsonData pageJsonData = GsonUtil.stringToObjectByBean(body, PageJsonData.class);
			if(pageJsonData != null){
				boolean result = foundCaseService.deleteFoundCaseById(pageJsonData.getId());
				if(result){
					resultbody = BodyProvider.getSuccessBody();
				}
			}
		} catch (Exception e) {
			DebugLog.logger.error("删除失物招领失败", e);
		}
		request.getSession().setAttribute(GlobalParams.RESULT,resultbody);
	}
	
	@RequestMapping("/refreshFoundCase.action")
	@ResponseBody
	public void refreshFoundCase(HttpServletRequest request) {
		Body resultbody =  BodyProvider.getFaildBody("置顶失物招领失败");
		try {
			String body = (String) request.getAttribute("body");
			PageJsonData pageJsonData = GsonUtil.stringToObjectByBean(body, PageJsonData.class);
			if(pageJsonData != null){
				boolean result = foundCaseService.refreshFoundCase(pageJsonData.getId(),new Date());
				if(result){
					resultbody = BodyProvider.getSuccessBody();
				}
			}
		} catch (Exception e) {
			DebugLog.logger.error("置顶失物招领失败", e);
		}
		request.getSession().setAttribute(GlobalParams.RESULT,resultbody);
	}
	
}
