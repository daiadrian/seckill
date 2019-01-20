package cn.dai.seckill.config;

import cn.dai.seckill.interceptor.UserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * Spring Boot 2.0后用配置类继承WebMvcConfigurerAdapter时，会提示这个类已经过时了
 *
 * 可以实现WebMvcConfigurer接口解决该问题
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Autowired
	UserInterceptor userInterceptor;
	@Autowired
	UserArgumentResolver userArgumentResolver;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(userInterceptor)
				.excludePathPatterns("/login/doRegister.do")
				.excludePathPatterns("/login/to_login.do");
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(userArgumentResolver);
	}
	
}
