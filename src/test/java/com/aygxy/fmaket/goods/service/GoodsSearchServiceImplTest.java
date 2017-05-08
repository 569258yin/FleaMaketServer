package com.aygxy.fmaket.goods.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.aygxy.fmaket.goods.entity.GoodsSearch;

public class GoodsSearchServiceImplTest {

	private static ApplicationContext context;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testSaveGoodsSearch() {
		GoodsSearchService gService = (GoodsSearchService) context.getBean("goodsSearchService");
		gService.saveGoodsSearch(new GoodsSearch());
	}

	@Test
	public void testDeleteGoodsSearch() {
		GoodsSearchService gService = (GoodsSearchService) context.getBean("goodsSearchService");
	}

	@Test
	public void testDeleteByGoodsId() {
		GoodsSearchService gService = (GoodsSearchService) context.getBean("goodsSearchService");
	}

	@Test
	public void testMatchKeyWords() {
		GoodsSearchService gService = (GoodsSearchService) context.getBean("goodsSearchService");
		List<GoodsSearch> lists = gService.matchKeyWords("天空");
		System.out.println(lists);
	}

}
