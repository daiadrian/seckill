package cn.dai.seckill.mapper;

import cn.dai.seckill.entity.Goods;
import cn.dai.seckill.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author adrian
 * @date 2018/10/27 13:29
 **/
@Mapper
public interface GoodsMapper {
    @Select("SELECT g.* FROM goods g")
    public List<Goods> getAllGoods();

    @Select("SELECT g.* FROM goods g WHERE id=#{id}")
    public List<Goods> getGoodsByGoodsId(@Param("id") Integer id);

    @Select("select g.*,sg.stock_count, sg.start_date, sg.end_date,sg.seckill_price from seckillGoods sg left join goods g on sg.goods_id = g.id")
    public List<GoodsVo> listGoodsVo();

    @Select("select g.*,sg.stock_count, sg.start_date, sg.end_date,sg.seckill_price from seckillGoods sg left join goods g on sg.goods_id = g.id where g.id = #{goodsId}")
    public GoodsVo getGoodsVoByGoodsId(@Param("goodsId") long goodsId);

    @Update("update goods set stock_count = stock_count - 1 where id = #{goodsId} and stock_count > 0")
    public int reduceStock(Goods g);

    @Update("update goods set stock_count = #{stockCount} where id = #{goodsId}")
    public int resetStock(Goods g);

}
