package com.aygxy.fmaket.goods.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aygxy.fmaket.debug.DebugLog;
import com.aygxy.fmaket.goods.entity.Goods;
import com.aygxy.fmaket.goods.entity.GoodsSearch;
import com.aygxy.fmaket.goods.entity.GoodsType;
import com.aygxy.fmaket.goods.entity.SearchTag;
import com.aygxy.fmaket.goods.service.GoodsSearchService;
import com.aygxy.fmaket.goods.service.GoodsService;
import com.aygxy.fmaket.goods.service.GoodsTypeService;
import com.aygxy.fmaket.goods.service.SearchTagService;
import com.aygxy.fmaket.net.jsonbean.PageJsonData;
import com.aygxy.fmaket.net.procatal.Body;
import com.aygxy.fmaket.net.procatal.BodyProvider;
import com.aygxy.fmaket.param.GlobalParams;
import com.aygxy.fmaket.util.GsonUtil;
import com.aygxy.fmaket.util.IKAnalyzerUtils;

@Controller
@RequestMapping("/goods")
public class GoodsAction {
	
	@Resource
	GoodsService goodsService;
	@Resource
	GoodsTypeService goodsTypeService;
	@Resource
	GoodsSearchService goodsSearchService;
	@Resource
	SearchTagService searchTagService;
	
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
				goods.setGoodsid(UUID.randomUUID().toString().replace("-", "")+goods.hashCode());
				goods.setGoodstime(new Date());
				goods.setModifyTime(goods.getGoodstime());
				boolean result = goodsService.saveGoods(goods);
				if(result){
					try{ 
						Set<String> keySets = IKAnalyzerUtils.segText(goods.getGoodstitle(), true);
						keySets.addAll(IKAnalyzerUtils.segText(goods.getGoodstext(), true));
						StringBuffer sb = new StringBuffer();
						for (String key : keySets) {
							sb.append(key).append(" ");
						}
						String keyWords = sb.toString();
						GoodsSearch goSearch = new GoodsSearch(UUID.randomUUID().toString().replace("-", "")+keyWords.hashCode(), goods.getGoodsid(), keyWords);
						goodsSearchService.saveGoodsSearch(goSearch);
					}catch (Exception e) {
						DebugLog.logger.error("添加关键字出错 "+body,e);
					}
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
	
	@RequestMapping("/getGoodsByPage.action")
	@ResponseBody
	public void getGoodsByPage(HttpServletRequest request){
		Body resultbody =  BodyProvider.getFaildBody("获取商品失败");
		try {
			String body = (String) request.getAttribute("body");
			PageJsonData pageJsonData = GsonUtil.stringToObjectByBean(body, PageJsonData.class);
			if(pageJsonData != null){
				List<Goods> lists;
				int type = pageJsonData.getType();
				if(type == GlobalParams.SELECT_GOODS_BY_TIME){
					lists = goodsService.selectGoodsByTime(pageJsonData.getPageNum(), pageJsonData.getPageSize());
				}else if(type == GlobalParams.SELECT_GOODS_BY_ADDRESS){
					lists = goodsService.selectGoodsByPage(pageJsonData.getPageNum(), pageJsonData.getPageSize());
				}else{
					lists = goodsService.selectGoodsByPage(pageJsonData.getPageNum(), pageJsonData.getPageSize());
				}
				
				String json = GsonUtil.objectToString(lists);
				resultbody = BodyProvider.getSuccessBody();
				resultbody.setElements(json);
			}
		} catch (Exception e) {
			DebugLog.logger.error("获取商品失败", e);
		}
		request.getSession().setAttribute(GlobalParams.RESULT,resultbody);
	}
	
	
	@RequestMapping("/getGoodsByGoodsTypeId.action")
	@ResponseBody
	public void getGoodsByGoodsTypeId(HttpServletRequest request){
		Body resultbody =  BodyProvider.getFaildBody("获取商品失败");
		try {
			String body = (String) request.getAttribute("body");
			PageJsonData pageJsonData = GsonUtil.stringToObjectByBean(body, PageJsonData.class);
			if(pageJsonData != null){
				List<Goods> lists = goodsService.selectGoodsByGoodsTypeId(pageJsonData.getPageNum(), pageJsonData.getPageSize(),
						pageJsonData.getType());
				String json = GsonUtil.objectToString(lists);
				resultbody = BodyProvider.getSuccessBody();
				resultbody.setElements(json);
			}
		} catch (Exception e) {
			DebugLog.logger.error("获取商品失败", e);
		}
		request.getSession().setAttribute(GlobalParams.RESULT,resultbody);
	}
	
	@RequestMapping("/getGoodsByUserId.action")
	@ResponseBody
	public void getGoodsByUserId(HttpServletRequest request){
		Body resultbody =  BodyProvider.getFaildBody("获取商品失败");
		try {
			String body = (String) request.getAttribute("body");
			PageJsonData pageJsonData = GsonUtil.stringToObjectByBean(body, PageJsonData.class);
			if(pageJsonData != null){
				List<Goods> lists = goodsService.selectGoodsByUserId(pageJsonData.getId());
				String json = GsonUtil.objectToString(lists);
				resultbody = BodyProvider.getSuccessBody();
				resultbody.setElements(json);
			}
		} catch (Exception e) {
			DebugLog.logger.error("获取商品失败", e);
		}
		request.getSession().setAttribute(GlobalParams.RESULT,resultbody);
	}
	
	@RequestMapping("/getGoodsByGoodsId.action")
	@ResponseBody
	public void getGoodsByGoodsId(HttpServletRequest request){
		Body resultbody =  BodyProvider.getFaildBody("获取商品失败");
		try {
			String body = (String) request.getAttribute("body");
			PageJsonData pageJsonData = GsonUtil.stringToObjectByBean(body, PageJsonData.class);
			if(pageJsonData != null){
				Goods goods = goodsService.selectGoodsById(pageJsonData.getId());
				String json = GsonUtil.objectToString(goods);
				resultbody = BodyProvider.getSuccessBody();
				resultbody.setElements(json);
			}
		} catch (Exception e) {
			DebugLog.logger.error("获取商品失败", e);
		}
		request.getSession().setAttribute(GlobalParams.RESULT,resultbody);
	}
	
	@RequestMapping("/updateGoods.action")
	@ResponseBody
	public void updateGoods(HttpServletRequest request){
		Body resultbody =  BodyProvider.getFaildBody("发布失败");
		try {
			String body = (String) request.getAttribute("body");
			Goods goods = GsonUtil.stringToObjectByBean(body, Goods.class);
			if(goods != null){
				goods.setModifyTime(new Date());
				boolean result = goodsService.updateGoods(goods);
				if(result){
					try{
						Set<String> keySets = IKAnalyzerUtils.segText(goods.getGoodstitle(), true);
						keySets.addAll(IKAnalyzerUtils.segText(goods.getGoodstext(), true));
						StringBuffer sb = new StringBuffer();
						for (String key : keySets) {
							sb.append(key).append(" ");
						}
						String keyWords = sb.toString();
						GoodsSearch goSearch = new GoodsSearch(UUID.randomUUID().toString().replace("-", "")+keyWords.hashCode(), goods.getGoodsid(), keyWords);
						goodsSearchService.saveGoodsSearch(goSearch);
					}catch (Exception e) {
						DebugLog.logger.error("添加关键字出错 "+body,e);
					}
					resultbody = BodyProvider.getSuccessBody();
				}
			}
		} catch (Exception e) {
			DebugLog.logger.error("发布宝贝失败", e);
		}
		request.getSession().setAttribute(GlobalParams.RESULT,resultbody);
	}
	
	@RequestMapping("/deleterGoods.action")
	@ResponseBody
	public void deleterGoods(HttpServletRequest request){
		Body resultbody =  BodyProvider.getFaildBody("发布失败");
		try {
			String body = (String) request.getAttribute("body");
			PageJsonData pageJsonData = GsonUtil.stringToObjectByBean(body, PageJsonData.class);
			if(pageJsonData != null){
				boolean result = goodsService.deleteGoods(pageJsonData.getId());
				if(result){
					try {
						goodsSearchService.deleteByGoodsId(pageJsonData.getId());
					} catch (Exception e) {
						DebugLog.logger.error("删除关键字出错 "+body);
					}
					
					resultbody = BodyProvider.getSuccessBody();
				}
			}
		} catch (Exception e) {
			DebugLog.logger.error("发布宝贝失败", e);
		}
		request.getSession().setAttribute(GlobalParams.RESULT,resultbody);
	}
	
	/**
	 * 置顶之前发布的内容
	 * @param request
	 */
	@RequestMapping("/refreshGoods.action")
	@ResponseBody
	public void refreshGoods(HttpServletRequest request){
		Body resultbody =  BodyProvider.getFaildBody("发布失败");
		try {
			String body = (String) request.getAttribute("body");
			PageJsonData pageJsonData = GsonUtil.stringToObjectByBean(body, PageJsonData.class);
			if(pageJsonData != null){
				boolean result = goodsService.refreshGoods(pageJsonData.getId(),new Date());
				if(result){
					resultbody = BodyProvider.getSuccessBody();
				}
			}
		} catch (Exception e) {
			DebugLog.logger.error("发布宝贝失败", e);
		}
		request.getSession().setAttribute(GlobalParams.RESULT,resultbody);
	}
	
	/**
	 * 搜索二手物品
	 * @param request
	 */
	@RequestMapping("/searchGoods.action")
	@ResponseBody
	public void searchGoods(HttpServletRequest request){
		Body resultbody =  BodyProvider.getFaildBody("搜索商品失败");
		try {
			String body = (String) request.getAttribute("body");
			PageJsonData pageJsonData = GsonUtil.stringToObjectByBean(body, PageJsonData.class);
			if(pageJsonData != null){
				Set<String> keySets = IKAnalyzerUtils.segText(pageJsonData.getId(), true);
				StringBuffer sb = new StringBuffer();
				for (String key : keySets) {
					sb.append(key).append(" ");
				}
				try {
					for (String key : keySets) {
						searchTagService.insertOrUpdate(new SearchTag(System.currentTimeMillis()+"_"+key.hashCode(), key, 1));
					}
				} catch (Exception e) {
					DebugLog.logger.error("插入统计关键字到表中出错： "+keySets,e);
				}
				List<GoodsSearch> keyLists = goodsSearchService.matchKeyWords(sb.toString());
				if(keyLists != null && keyLists.size() > 0){
					List<Goods> goodsList = new ArrayList<>(keyLists.size());
					for (GoodsSearch goodsSearch : keyLists) {
						Goods goods = goodsService.selectGoodsById(goodsSearch.getGoodsId());
						goodsList.add(goods);
					}
					String json = GsonUtil.objectToString(goodsList);
					resultbody = BodyProvider.getSuccessBody();
					resultbody.setElements(json);
				}else{
					resultbody = BodyProvider.getSuccessBody();
				}
			}
		} catch (Exception e) {
			DebugLog.logger.error("搜索商品失败", e);
		}
		request.getSession().setAttribute(GlobalParams.RESULT,resultbody);
	}
	
	/**
	 * 获取搜索关键字
	 * @param request
	 */
	@RequestMapping("/getSearchTop.action")
	@ResponseBody
	public void getSearchTop(HttpServletRequest request){
		Body resultbody =  BodyProvider.getFaildBody("获取搜索关键字");
		try {
			String body = (String) request.getAttribute("body");
			PageJsonData pageJsonData = GsonUtil.stringToObjectByBean(body, PageJsonData.class);
			if(pageJsonData != null){
				List<SearchTag> lists = searchTagService.selectTop(pageJsonData.getPageSize());
				if(lists != null){
					List<String> resList = new ArrayList<>(lists.size());
					for (SearchTag searchTag : lists) {
						resList.add(searchTag.getTag());
					}
					String json = GsonUtil.objectToString(resList);
					resultbody = BodyProvider.getSuccessBody();
					resultbody.setElements(json);
				}
			}
		} catch (Exception e) {
			DebugLog.logger.error("获取搜索关键字", e);
		}
		request.getSession().setAttribute(GlobalParams.RESULT,resultbody);
	}
	
	
	
	
}
