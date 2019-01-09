package cn.dai.seckill.interceptor;

import cn.dai.seckill.entity.User;
import cn.dai.seckill.service.LoginService;
import cn.dai.seckill.service.impl.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private LoginService loginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        User user = getUser(request);
        if (user == null){
            response.sendRedirect(request.getContextPath() + "/login/to_login.do");
            return false;
        }
        return true;
    }

    private User getUser(HttpServletRequest request){
        String cookieToken = getCookieValue(request, LoginServiceImpl.TOKEN_NAME);
        if(StringUtils.isEmpty(cookieToken)) {
            return null;
        }
        return loginService.getByToken(cookieToken);
    }

    private String getCookieValue(HttpServletRequest request, String cookiName) {
        Cookie[]  cookies = request.getCookies();
        if(cookies == null || cookies.length <= 0){
            return null;
        }
        for(Cookie cookie : cookies) {
            if(cookie.getName().equals(cookiName)) {
                return cookie.getValue();
            }
        }
        return null;
    }

}
