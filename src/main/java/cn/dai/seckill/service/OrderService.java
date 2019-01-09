package cn.dai.seckill.service;

import cn.dai.seckill.entity.OrderInfo;
import cn.dai.seckill.entity.User;
import cn.dai.seckill.vo.GoodsVo;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author adrian
 * @date 2019/1/7 11:15
 **/
public interface OrderService {
	
	public OrderInfo getOrderById(long orderId);

	@Transactional
	public OrderInfo createOrder(User user, GoodsVo goods);

	public void deleteOrders();

}
