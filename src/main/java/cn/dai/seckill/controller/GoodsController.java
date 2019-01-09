package cn.dai.seckill.controller;

import cn.dai.seckill.entity.User;
import cn.dai.seckill.result.Results;
import cn.dai.seckill.service.GoodsService;
import cn.dai.seckill.type.RedisDBEnum;
import cn.dai.seckill.util.JedisUtils;
import cn.dai.seckill.vo.GoodsDetailVo;
import cn.dai.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.IWebContext;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author adrian
 * @date 2019/1/7 10:50
 **/
@Controller
@RequestMapping("/goods")
public class GoodsController {
	
	@Autowired
	JedisUtils jedisUtils;
	@Autowired
	GoodsService goodsService;
	@Autowired
	ThymeleafViewResolver thymeleafViewResolver;

	/**
	 * 注意点：因为在thymeleaf.spring5的API中把大部分的功能移到了IWebContext下面,用来区分边界。
	 * 		  剔除了ApplicationContext 过多的依赖，现在thymeleaf渲染不再过多依赖spring容器。
	 *
	 * 		  在spring4中:
	 * SpringWebContext ctx = new SpringWebContext(request,response,
	 * 					request.getServletContext(),request.getLocale(), model.asMap(), applicationContext );
	 * String html = thymeleafViewResolver.getTemplateEngine().process("goods_list", ctx);
	 *
	 * RequestMapping中produces属性设置返回数据的类型以及编码;必须与@ResponseBody注解使用
	 *
	 * @param request
	 * @param response
	 * @param model
	 * @param user
	 * @return
	 */
    @RequestMapping(value="/to_list", produces="text/html")
    @ResponseBody
    public String list(HttpServletRequest request, HttpServletResponse response, Model model, User user) {
    	model.addAttribute("user", user);
    	//取缓存
//    	String html = redisService.get(GoodsKey.getGoodsList, "", String.class);
//    	if(!StringUtils.isEmpty(html)) {
//    		return html;
//    	}
    	List<GoodsVo> goodsList = goodsService.listGoodsVo();
    	model.addAttribute("goodsList", goodsList);
//    	 return "goods_list";
    	IWebContext webContext = new WebContext(request,response,
    			request.getServletContext(),request.getLocale(), model.asMap());
    	//手动渲染(goods_list指的是templates文件夹下对应html的名称)
    	String html = thymeleafViewResolver.getTemplateEngine().process("goods_list", webContext);
    	if(!StringUtils.isEmpty(html)) {
			jedisUtils.set("key", html, RedisDBEnum.GOODS_DB.getDb());
    	}
    	return html;
    }
    
    @RequestMapping(value="/to_detail2/{goodsId}",produces="text/html")
    @ResponseBody
    public String detail2(HttpServletRequest request, HttpServletResponse response, Model model, User user,
                          @PathVariable("goodsId")long goodsId) {
    	model.addAttribute("user", user);
    	
    	//取缓存
    	String html = jedisUtils.get("key", RedisDBEnum.GOODS_DB.getDb());
    	if(!StringUtils.isEmpty(html)) {
    		return html;
    	}
    	//手动渲染
    	GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
    	model.addAttribute("goods", goods);
    	
    	long startAt = goods.getStartDate().getTime();
    	long endAt = goods.getEndDate().getTime();
    	long now = System.currentTimeMillis();
    	
    	int miaoshaStatus = 0;
    	int remainSeconds = 0;
    	if(now < startAt ) {//秒杀还没开始，倒计时
    		miaoshaStatus = 0;
    		remainSeconds = (int)((startAt - now )/1000);
    	}else  if(now > endAt){//秒杀已经结束
    		miaoshaStatus = 2;
    		remainSeconds = -1;
    	}else {//秒杀进行中
    		miaoshaStatus = 1;
    		remainSeconds = 0;
    	}
    	model.addAttribute("miaoshaStatus", miaoshaStatus);
    	model.addAttribute("remainSeconds", remainSeconds);
//        return "goods_detail";

		IWebContext webContext = new WebContext(request,response,
				request.getServletContext(),request.getLocale(), model.asMap());
		//手动渲染
		html = thymeleafViewResolver.getTemplateEngine().process("goods_list", webContext);
    	if(!StringUtils.isEmpty(html)) {
    		jedisUtils.set("key", html, RedisDBEnum.GOODS_DB.getDb());
    	}
    	return html;
    }
    
    @RequestMapping(value="/detail/{goodsId}")
    @ResponseBody
    public Results<GoodsDetailVo> detail(HttpServletRequest request, HttpServletResponse response, Model model, User user,
										 @PathVariable("goodsId")long goodsId) {
    	GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
    	long startAt = goods.getStartDate().getTime();
    	long endAt = goods.getEndDate().getTime();
    	long now = System.currentTimeMillis();
    	int miaoshaStatus = 0;
    	int remainSeconds = 0;
    	if(now < startAt ) {//秒杀还没开始，倒计时
    		miaoshaStatus = 0;
    		remainSeconds = (int)((startAt - now )/1000);
    	}else  if(now > endAt){//秒杀已经结束
    		miaoshaStatus = 2;
    		remainSeconds = -1;
    	}else {//秒杀进行中
    		miaoshaStatus = 1;
    		remainSeconds = 0;
    	}
    	GoodsDetailVo vo = new GoodsDetailVo();
    	vo.setGoods(goods);
    	vo.setUser(user);
    	vo.setRemainSeconds(remainSeconds);
    	vo.setMiaoshaStatus(miaoshaStatus);
    	return Results.success(vo);
    }
    
    
}
