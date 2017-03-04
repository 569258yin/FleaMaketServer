package com.aygxy.fmaket.goods.dao;

import org.springframework.stereotype.Repository;

import com.aygxy.fmaket.goods.entity.GoodsRepaly;

@Repository
public interface GoodsRepalyMapper {
    int deleteByPrimaryKey(String goodsreplayid);

    int insert(GoodsRepaly record);

    int insertSelective(GoodsRepaly record);

    GoodsRepaly selectByPrimaryKey(String goodsreplayid);

    int updateByPrimaryKeySelective(GoodsRepaly record);

    int updateByPrimaryKey(GoodsRepaly record);
}