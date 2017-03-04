package com.aygxy.fmaket.foundcase.dao;

import org.springframework.stereotype.Repository;

import com.aygxy.fmaket.foundcase.entity.FoundCase;


@Repository
public interface FoundCaseMapper {
    int deleteByPrimaryKey(String fdcid);

    int insert(FoundCase record);

    int insertSelective(FoundCase record);

    FoundCase selectByPrimaryKey(String fdcid);

    int updateByPrimaryKeySelective(FoundCase record);

    int updateByPrimaryKey(FoundCase record);
}