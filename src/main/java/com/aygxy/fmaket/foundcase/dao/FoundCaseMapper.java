package com.aygxy.fmaket.foundcase.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.stereotype.Repository;

import com.aygxy.fmaket.foundcase.entity.FoundCase;


@Repository
public interface FoundCaseMapper {
    int deleteByPrimaryKey(String fdcid);

    int insert(FoundCase record);

    int insertSelective(FoundCase record);

    FoundCase selectByPrimaryKey(String fdcid);
    
    List<FoundCase> selectAllByType(@Param("fdcType")int fdcType,@Param("pageNumKey") int pageNum,@Param("pageSizeKey") int pageSize);
    
    List<FoundCase> selectAllFromUser(@Param("userId")String userId);
    
    int updateByPrimaryKeySelective(FoundCase record);

    int updateByPrimaryKey(FoundCase record);
}