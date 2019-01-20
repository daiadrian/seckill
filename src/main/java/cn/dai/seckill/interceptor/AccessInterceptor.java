package cn.dai.seckill.interceptor;

import cn.dai.seckill.result.CodeMsg;
import cn.dai.seckill.result.Results;
import cn.dai.seckill.type.RedisDBEnum;
import cn.dai.seckill.util.JedisUtils;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

@Service
public class AccessInterceptor  extends HandlerInterceptorAdapter {
	
	@Autowired
	JedisUtils jedisUtils;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(handler instanceof HandlerMethod) {
			HandlerMethod hm = (HandlerMethod)handler;
			AccessLimit accessLimit = hm.getMethodAnnotation(AccessLimit.class);
			if(accessLimit == null) {
				return true;
			}
			int seconds = accessLimit.seconds();
			int maxCount = accessLimit.maxCount();
			String key = request.getRequestURI();
			String count = jedisUtils.get(key, RedisDBEnum.ACCESS_BD.getDb());
	    	if(count == null) {
				jedisUtils.setex("access" + key, String.valueOf(1), seconds, RedisDBEnum.ACCESS_BD.getDb());
	    	}else if(Integer.valueOf(count) < maxCount) {
				jedisUtils.incr("access" + key, RedisDBEnum.ACCESS_BD.getDb());
	    	}else {
	    		render(response, CodeMsg.ACCESS_LIMIT_REACHED);
	    		return false;
	    	}
		}
		return true;
	}
	
	private void render(HttpServletResponse response, CodeMsg cm)throws Exception {
		response.setContentType("application/json;charset=UTF-8");
		OutputStream out = response.getOutputStream();
		String str  = JSON.toJSONString(Results.faild(cm));
		out.write(str.getBytes("UTF-8"));
		out.flush();
		out.close();
	}
	
}
