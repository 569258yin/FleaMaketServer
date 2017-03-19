package com.aygxy.fmaket.goods.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aygxy.fmaket.debug.DebugLog;
import com.aygxy.fmaket.goods.entity.Goods;
import com.aygxy.fmaket.goods.entity.GoodsType;
import com.aygxy.fmaket.goods.service.GoodsService;
import com.aygxy.fmaket.goods.service.GoodsTypeService;
import com.aygxy.fmaket.net.procatal.Body;
import com.aygxy.fmaket.net.procatal.BodyProvider;
import com.aygxy.fmaket.param.GlobalParams;
import com.aygxy.fmaket.util.GsonUtil;

@Controller
@RequestMapping("/goods")
public class GoodsAction {
	
	@Resource
	GoodsService goodsService;
	@Resource
	GoodsTypeService goodsTypeService;
	
	@RequestMapping("/test.action")
	@ResponseBody
	public String test(Model model){
		return "SUCCESS TOKEN";
	}
	
	@RequestMapping("/sendGoods.action")
	@ResponseBody
	public void sendGoods(HttpServletRequest request){
		Body resultbody =  BodyProvider.getFaildBody("发布失败");
		try {
			String body = (String) request.getAttribute("body");
			Goods goods = GsonUtil.stringToObjectByBean(body, Goods.class);
			if(goods != null){
				goods.setGoodsid(UUID.randomUUID().toString()+(int) (100000 + Math.random()*999999));
				goods.setGoodstime(new Date());
				boolean result = goodsService.saveGoods(goods);
				if(result){
					resultbody = BodyProvider.getSuccessBody();
				}
			}
		} catch (Exception e) {
			DebugLog.logger.error("发布宝贝失败", e);
		}
		request.getSession().setAttribute(GlobalParams.RESULT,resultbody);
	}
	
	@RequestMapping("/getGoodsType.action")
	@ResponseBody
	public void getGoodsType(HttpServletRequest request){
		Body resultbody =  BodyProvider.getFaildBody("获取分类失败");
		try {
			List<GoodsType> goodsTypeList = goodsTypeService.getAllGoodsType();
			String json = GsonUtil.objectToString(goodsTypeList);
			resultbody = BodyProvider.getSuccessBody();
			resultbody.setElements(json);
		} catch (Exception e) {
			DebugLog.logger.error("发布宝贝失败", e);
		}
		request.getSession().setAttribute(GlobalParams.RESULT,resultbody);
	}
}
