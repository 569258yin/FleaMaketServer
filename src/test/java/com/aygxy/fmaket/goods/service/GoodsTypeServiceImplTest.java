package com.aygxy.fmaket.goods.service;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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

}
