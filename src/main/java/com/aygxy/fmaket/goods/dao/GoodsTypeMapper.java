package com.aygxy.fmaket.goods.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.aygxy.fmaket.goods.entity.GoodsType;

@Repository
public interface GoodsTypeMapper {
    int deleteByPrimaryKey(Integer goodstypeid);

    int insert(GoodsType record);

    int insertSelective(GoodsType record);

    GoodsType selectByPrimaryKey(Integer goodstypeid);
    
    List<GoodsType> selectAllGoodsType();

    int updateByPrimaryKeySelective(GoodsType record);

    int updateByPrimaryKey(GoodsType record);
}