package cn.dai.seckill.mapper;

import cn.dai.seckill.entity.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
}
