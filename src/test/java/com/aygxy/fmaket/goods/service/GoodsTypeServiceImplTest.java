package com.aygxy.fmaket.goods.service;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.aygxy.fmaket.goods.entity.Goods;
import com.aygxy.fmaket.util.GsonUtil;

public class GoodsTypeServiceImplTest {

	private static ApplicationContext context;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	

	@Test
	public void testGetAllGoodsType() {
		GoodsTypeService goodsTypeService = (GoodsTypeService) context.getBean("goodsTypeService");
		
		System.out.println(goodsTypeService.getAllGoodsType());
	}
	
	@Test
	public void testPageHelper(){
		GoodsService goodsService = (GoodsService) context.getBean("goodsService");
		List<Goods>  goodsList = goodsService.selectGoodsByPage(1, 2);
		String json = GsonUtil.objectToString(goodsList);
		System.out.println(json);
	}

}
