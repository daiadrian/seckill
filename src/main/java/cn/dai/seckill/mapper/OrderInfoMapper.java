package cn.dai.seckill.mapper;

import cn.dai.seckill.entity.OrderInfo;
import org.apache.ibatis.annotations.*;

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

    @Insert("insert into order_info(user_id, goods_id, goods_name, goods_count, goods_price, order_channel, status, create_date)values("
            + "#{userId}, #{goodsId}, #{goodsName}, #{goodsCount}, #{goodsPrice}, #{orderChannel},#{status},#{createDate} )")
    @SelectKey(keyColumn="id", keyProperty="id", resultType=long.class, before=false, statement="select last_insert_id()")
    public long insert(OrderInfo orderInfo);

    @Select("select * from order_info where id = #{orderId}")
    public OrderInfo getOrderById(@Param("orderId") long orderId);

    @Delete("delete from order_info")
    public void deleteOrders();

    @Delete("delete from miaosha_order")
    public void deleteMiaoshaOrders();

}
