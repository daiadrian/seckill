package cn.dai.seckill.service.impl;

import cn.dai.seckill.entity.OrderInfo;
import cn.dai.seckill.entity.User;
import cn.dai.seckill.mapper.OrderInfoMapper;
import cn.dai.seckill.service.OrderService;
import cn.dai.seckill.util.JedisUtils;
import cn.dai.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author adrian
 * @date 2019/1/7 11:15
 **/
@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	OrderInfoMapper orderInfoMapper;
	
	@Autowired
	JedisUtils jedisUtils;
	
	public OrderInfo getOrderById(long orderId) {
		return orderInfoMapper.getOrderById(orderId);
	}
	

	@Transactional
	public OrderInfo createOrder(User user, GoodsVo goods) {
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setCreateDate(new Date());
		orderInfo.setGoodsCount(1);
		orderInfo.setGoodsId(goods.getId());
		orderInfo.setGoodsName(goods.getGoodsName());
		orderInfo.setGoodsPrice(goods.getSeckillPrice());
		orderInfo.setStatus(0);
		orderInfo.setUserId(user.getId());
		orderInfoMapper.insert(orderInfo);
		 
		return orderInfo;
	}

	public void deleteOrders() {
		orderInfoMapper.deleteOrders();
		orderInfoMapper.deleteMiaoshaOrders();
	}

}
