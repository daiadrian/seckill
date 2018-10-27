package cn.dai.seckill.mapper;

import cn.dai.seckill.entity.SeckillGoods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author adrian
 * @date 2018/10/27 13:35
 **/
@Mapper
public interface SeckillGoodsMapper {

    @Select("SELECT s.* FROM seckillGoods s WHERE goodsId=#{goodsId}")
    public SeckillGoods findSeckillGoodsByGoodsId(@Param("goodsId") Integer goodsId);

}
