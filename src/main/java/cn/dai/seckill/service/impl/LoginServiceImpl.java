package cn.dai.seckill.service.impl;

import cn.dai.seckill.entity.User;
import cn.dai.seckill.exception.GobalException;
import cn.dai.seckill.mapper.UserMapper;
import cn.dai.seckill.result.CodeMsg;
import cn.dai.seckill.service.LoginService;
import cn.dai.seckill.util.JedisUtils;
import cn.dai.seckill.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author adrian
 * @date 2018/10/27 14:13
 **/
@Service
public class LoginServiceImpl implements LoginService {

    private static final String TOKEN_NAME = "seckill-token";
    private static final String USER_COOKIE = "USER_COOKIE";

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JedisUtils jedisUtils;

    public boolean login(HttpServletResponse response, User user){
        User userByMobile = userMapper.getByMobile(user.getMobile());
        if (userByMobile == null){
            throw new GobalException(CodeMsg.MOBILE_NOT_EXIEST.getMsg(), CodeMsg.MOBILE_NOT_EXIEST.getStatus());
        }
        //验证密码
        String salt = userByMobile.getSalt();
        String password = userByMobile.getPassword();
        String userP = MD5Util.md5(password, salt);
        if (!user.getPassword().equals(userP)){
            throw new GobalException(CodeMsg.PASSWORD_ERROR.getMsg(), CodeMsg.PASSWORD_ERROR.getStatus());
        }
        //生成cookie
        //对cookie进行加盐操作并存放到redis中
        String uuid = UUID.randomUUID().toString();
        String cookieStr = MD5Util.md5(uuid, salt);
        jedisUtils.set(USER_COOKIE + user.getId(), cookieStr, 0);
        jedisUtils.expire(USER_COOKIE + user.getId(), 3600, 0);
        addCookie(response, uuid);
        return true;
    }

    public void addCookie(HttpServletResponse response, String uuid){
        Cookie cookie = new Cookie(TOKEN_NAME, uuid);
        cookie.setMaxAge(3600);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

}
