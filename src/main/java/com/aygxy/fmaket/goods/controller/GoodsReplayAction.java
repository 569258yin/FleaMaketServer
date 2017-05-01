package com.aygxy.fmaket.goods.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aygxy.fmaket.debug.DebugLog;
import com.aygxy.fmaket.goods.entity.GoodsRepaly;
import com.aygxy.fmaket.goods.entity.ToRepaly;
import com.aygxy.fmaket.goods.service.GoodsReplayService;
import com.aygxy.fmaket.net.jsonbean.PageJsonData;
import com.aygxy.fmaket.net.procatal.Body;
import com.aygxy.fmaket.net.procatal.BodyProvider;
import com.aygxy.fmaket.param.GlobalParams;
import com.aygxy.fmaket.util.GsonUtil;

@RequestMapping("/goodsReplay")
@Controller
public class GoodsReplayAction {
	
	@Resource
	GoodsReplayService goodsReplayService;
	
	@RequestMapping("/sendReplay.action")
	@ResponseBody
	public void sendReplay(HttpServletRequest request){
		Body resultbody =  BodyProvider.getFaildBody("发布失败");
		try {
			String body = (String) request.getAttribute("body");
			GoodsRepaly goodsRepaly = GsonUtil.stringToObjectByBean(body, GoodsRepaly.class);
			if(goodsRepaly != null){
				goodsRepaly.setGoodsreplayid(UUID.randomUUID().toString().replace("-", "")+goodsRepaly.hashCode());
				goodsRepaly.setGoodsreplaytime(new Date());
				boolean bool = goodsReplayService.saveGoodsReplay(goodsRepaly);
				if(bool){
					resultbody = BodyProvider.getSuccessBody();
				}
			}
			
		}catch (Exception e) {
			DebugLog.logger.error("发布商品评论失败", e);
		}
		request.getSession().setAttribute(GlobalParams.RESULT,resultbody);
	}
	
	
	@RequestMapping("/getAllGoodsReplay.action")
	@ResponseBody
	public void getAllGoodsReplayFromGoodsId(HttpServletRequest request){
		Body resultbody =  BodyProvider.getFaildBody("获取评论失败");
		try {
			String body = (String) request.getAttribute("body");
			PageJsonData pageJsonData = GsonUtil.stringToObjectByBean(body, PageJsonData.class);
			if(pageJsonData != null){
				List<GoodsRepaly> lists = goodsReplayService.selectAllByGoodsId(pageJsonData.getId());
				String json = GsonUtil.objectToString(lists);
				resultbody = BodyProvider.getSuccessBody();
				resultbody.setElements(json);
			}
		}catch (Exception e) {
			DebugLog.logger.error("获取商品评论失败", e);
		}
		request.getSession().setAttribute(GlobalParams.RESULT,resultbody);
	}
	
	
	@RequestMapping("/sendToReplay.action")
	@ResponseBody
	public void sendToReplay(HttpServletRequest request){
		Body resultbody =  BodyProvider.getFaildBody("发布失败");
		try {
			String body = (String) request.getAttribute("body");
			ToRepaly toRepaly = GsonUtil.stringToObjectByBean(body, ToRepaly.class);
			if(toRepaly != null) {
				toRepaly.setTorepalyid(UUID.randomUUID().toString().replace("-", "")+toRepaly.hashCode());
				toRepaly.setTorepalytime(new Date());
				boolean bool = goodsReplayService.saveToReplay(toRepaly);
				if(bool){
					resultbody = BodyProvider.getSuccessBody();
				}
			}
		}catch (Exception e) {
			DebugLog.logger.error("回复商品评论失败", e);
		}
		request.getSession().setAttribute(GlobalParams.RESULT,resultbody);
	}
	
}
