package com.aygxy.fmaket.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.aygxy.fmaket.goods.entity.GoodsRepaly;

@Repository
public interface GoodsRepalyMapper {
	
	List<GoodsRepaly> selectAllFromGoodsId(@Param("goodsid") String goodsid);
	
    int deleteByPrimaryKey(String goodsreplayid);

    int insert(GoodsRepaly record);

    int insertSelective(GoodsRepaly record);

    GoodsRepaly selectByPrimaryKey(String goodsreplayid);

    int updateByPrimaryKeySelective(GoodsRepaly record);

    int updateByPrimaryKey(GoodsRepaly record);
}