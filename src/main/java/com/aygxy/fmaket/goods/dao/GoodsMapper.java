package com.aygxy.fmaket.goods.dao;

import org.springframework.stereotype.Repository;

import com.aygxy.fmaket.goods.entity.Goods;

@Repository
public interface GoodsMapper {
    int deleteByPrimaryKey(String goodsid);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(String goodsid);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKeyWithBLOBs(Goods record);

    int updateByPrimaryKey(Goods record);
}