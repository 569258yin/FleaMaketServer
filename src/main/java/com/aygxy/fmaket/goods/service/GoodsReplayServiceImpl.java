package com.aygxy.fmaket.goods.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.aygxy.fmaket.goods.dao.GoodsRepalyMapper;
import com.aygxy.fmaket.goods.dao.ToRepalyMapper;
import com.aygxy.fmaket.goods.entity.GoodsRepaly;
import com.aygxy.fmaket.goods.entity.ToRepaly;
@Service("goodsReplayService")
public class GoodsReplayServiceImpl implements GoodsReplayService {
	
	@Resource
	GoodsRepalyMapper goodsRepalyMapper;
	@Resource
	ToRepalyMapper toRepalyMapper;
	
	@Override
	public List<GoodsRepaly> selectAllByGoodsId(String goodsId) {
		List<GoodsRepaly> lists = goodsRepalyMapper.selectAllFromGoodsId(goodsId);
		if(lists != null){
			for (GoodsRepaly goodsRepaly : lists) {
				List<ToRepaly> toLists = toRepalyMapper.selectAllFromRepalyId(goodsRepaly.getGoodsreplayid());
				goodsRepaly.setTorepalyList(toLists);
			}
		}
		return lists;
	}

	@Override
	public boolean saveGoodsReplay(GoodsRepaly goodsRepaly) {
		int count = goodsRepalyMapper.insert(goodsRepaly);
		return count == 1;
	}

	@Override
	public boolean saveToReplay(ToRepaly toRepaly) {
		int count = toRepalyMapper.insert(toRepaly);
		return count == 1;
	}

	

}
