package cn.dai.seckill.mapper;

import cn.dai.seckill.entity.OrderInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author adrian
 * @date 2018/10/27 13:31
 **/
@Mapper
public interface OrderInfoMapper {
    @Select("SELECT o.* FROM OrderInfo o WHERE userId=#{id}")
    public List<OrderInfo> getOrderInfoByUserId(@Param("id") Integer id);

    @Select("SELECT o.* FROM OrderInfo o WHERE userId=#{userId} AND goodsId=#{goodsId}")
    public OrderInfo getOrderInfoByUserIdAndGoodsId(@Param("userId") Integer userId, @Param("goodsId") Integer goodsId);

}
