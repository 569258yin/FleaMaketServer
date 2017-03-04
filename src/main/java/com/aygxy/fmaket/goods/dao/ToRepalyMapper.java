package com.aygxy.fmaket.goods.dao;

import org.springframework.stereotype.Repository;

import com.aygxy.fmaket.goods.entity.ToRepaly;

@Repository
public interface ToRepalyMapper {
    int deleteByPrimaryKey(String torepalyid);

    int insert(ToRepaly record);

    int insertSelective(ToRepaly record);

    ToRepaly selectByPrimaryKey(String torepalyid);

    int updateByPrimaryKeySelective(ToRepaly record);

    int updateByPrimaryKey(ToRepaly record);
}