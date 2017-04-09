package com.aygxy.fmaket.goods.service;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GoodsReplayServiceImplTest {

	private static ApplicationContext context;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testSelectAllByGoodsId() {
		GoodsReplayService goodsReplayService = (GoodsReplayService) context.getBean("goodsReplayService");
		goodsReplayService.selectAllByGoodsId("2473683a76f44aafbaa873a617d5b40a12842350");
	}

}
