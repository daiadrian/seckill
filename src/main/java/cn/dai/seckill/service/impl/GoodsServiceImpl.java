package cn.dai.seckill.service.impl;

import cn.dai.seckill.entity.Goods;
import cn.dai.seckill.mapper.GoodsMapper;
import cn.dai.seckill.service.GoodsService;
import cn.dai.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	GoodsMapper goodsMapper;

	public List<GoodsVo> listGoodsVo(){
		return goodsMapper.listGoodsVo();
	}

	public GoodsVo getGoodsVoByGoodsId(long goodsId) {
		return goodsMapper.getGoodsVoByGoodsId(goodsId);
	}

	public boolean reduceStock(GoodsVo goods) {
		Goods g = new Goods();
		g.setId(goods.getId());
		int ret = goodsMapper.reduceStock(g);
		return ret > 0;
	}

	public void resetStock(List<GoodsVo> goodsList) {
		for(GoodsVo goods : goodsList ) {
			Goods g = new Goods();
			g.setId(goods.getId());
			g.setGoodsStock(goods.getStockCount());
			goodsMapper.resetStock(g);
		}
	}

}
