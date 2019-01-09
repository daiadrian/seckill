package cn.dai.seckill.controller;

import cn.dai.seckill.entity.OrderInfo;
import cn.dai.seckill.entity.User;
import cn.dai.seckill.result.CodeMsg;
import cn.dai.seckill.result.Results;
import cn.dai.seckill.service.GoodsService;
import cn.dai.seckill.service.OrderService;
import cn.dai.seckill.util.JedisUtils;
import cn.dai.seckill.vo.GoodsVo;
import cn.dai.seckill.vo.OrderDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author adrian
 * @date 2019/1/7 11:15
 **/
@Controller
@RequestMapping("/order")
public class OrderController {

	
	@Autowired
	JedisUtils jedisUtils;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	GoodsService goodsService;
	
    @RequestMapping("/detail")
    @ResponseBody
    public Results<OrderDetailVo> info(Model model, User user,
									   @RequestParam("orderId") long orderId) {
    	if(user == null) {
    		return Results.faild(CodeMsg.LOGIN_FAIL.getMsg());
    	}
    	OrderInfo order = orderService.getOrderById(orderId);
    	if(order == null) {
    		return Results.faild(CodeMsg.LOGIN_FAIL.getMsg());
    	}
    	long goodsId = order.getGoodsId();
    	GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
    	OrderDetailVo vo = new OrderDetailVo();
    	vo.setOrder(order);
    	vo.setGoods(goods);
    	return Results.success(vo);
    }
    
}
