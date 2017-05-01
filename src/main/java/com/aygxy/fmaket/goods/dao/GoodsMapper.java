package com.aygxy.fmaket.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.aygxy.fmaket.goods.entity.Goods;

@Repository
public interface GoodsMapper {
    int deleteByPrimaryKey(String goodsid);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(@Param("goodsid")String goodsid);
    
    /**
     * 分页查询所有商品信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<Goods> selectGoodsByPage(@Param("pageNumKey") int pageNum,@Param("pageSizeKey") int pageSize);
    
    /**
     * 分页查询所有商品信息 根据时间排序
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<Goods> selectGoodsByPageOrderTime(@Param("pageNumKey") int pageNum,@Param("pageSizeKey") int pageSize);
    
    /**
     * 分页查询所有信息，根据地理位置
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<Goods> selectGoodsByPageOrderAddress(@Param("pageNumKey") int pageNum,@Param("pageSizeKey") int pageSize);
    
    /**
     * 分页查询所有商品信息 根据时间排序
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<Goods> selectGoodsByGoodsTypeId(@Param("pageNumKey") int pageNum,@Param("pageSizeKey") int pageSize,@Param("goodsTypeId")int goodsTypeId);
    
    /**
     * 查询指定用户发布的商品
     * @param userId
     * @return
     */
    List<Goods> selectGoodsByUserId(@Param("userId") String userId);
    
    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);
}