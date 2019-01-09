package cn.dai.seckill.service;

import cn.dai.seckill.vo.GoodsVo;

import java.util.List;


public interface GoodsService {
	
	public List<GoodsVo> listGoodsVo();

	public GoodsVo getGoodsVoByGoodsId(long goodsId);

	public boolean reduceStock(GoodsVo goods);

	public void resetStock(List<GoodsVo> goodsList);
	
}
