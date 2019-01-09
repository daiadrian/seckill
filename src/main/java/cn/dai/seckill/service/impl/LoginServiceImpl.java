package cn.dai.seckill.service.impl;

import cn.dai.seckill.entity.User;
import cn.dai.seckill.exception.GobalException;
import cn.dai.seckill.mapper.UserMapper;
import cn.dai.seckill.result.CodeMsg;
import cn.dai.seckill.service.LoginService;
import cn.dai.seckill.type.RedisDBEnum;
import cn.dai.seckill.util.JedisUtils;
import cn.dai.seckill.util.MD5Util;
import cn.dai.seckill.util.T;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.UUID;

/**
 * @author adrian
 * @date 2018/10/27 14:13
 **/
@Service
public class LoginServiceImpl implements LoginService {

    public static final String TOKEN_NAME = "seckill-token";
    public static final Integer USER_MAX_COOKIE_TIME = 3600;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JedisUtils jedisUtils;

    /**
     * 登录操作
     * @param response
     * @param user
     * @return
     */
    @Transactional
    public boolean login(HttpServletResponse response, User user){
        User userByMobile = userMapper.getByMobile(user.getMobile());
        if (userByMobile == null){
            throw new GobalException(CodeMsg.MOBILE_NOT_EXIEST.getMsg(), CodeMsg.MOBILE_NOT_EXIEST.getStatus());
        }
        //验证密码
        String salt = userByMobile.getSalt();
        String password = userByMobile.getPassword();
        String userP = MD5Util.md5(user.getPassword(), salt);
        if (!password.equals(userP)){
            throw new GobalException(CodeMsg.PASSWORD_ERROR.getMsg(), CodeMsg.PASSWORD_ERROR.getStatus());
        }
        //生成cookie
        String uuid = UUID.randomUUID().toString();
        String cookieStr = MD5Util.md5(uuid, salt);
        addCookieAndRedis(response, uuid, cookieStr, userByMobile);
        //记录登录次数
        Date now = T.getNow();
        userByMobile.setLastLoginDate(now);
        userByMobile.setUpdateDate(now);
        int newCount = userByMobile.getLoginCount() + 1;
        userMapper.userLoginIn(userByMobile, newCount);
        return true;
    }

    @Override
    @Transactional
    public boolean register(User user) {
        User byMobile = userMapper.getByMobile(user.getMobile());
        if (byMobile != null){
            throw new GobalException(CodeMsg.MOBILE_EXIEST.getMsg(), CodeMsg.MOBILE_EXIEST.getStatus());
        }
        String userP = MD5Util.md5(user.getPassword(), MD5Util.slat);
        user.setPassword(userP);
        user.setLoginCount(0);
        user.setSalt(MD5Util.slat);
        Date now = T.getNow();
        user.setRegisterDate(now);
        user.setCreateDate(now);
        user.setUpdateDate(now);
        long id = userMapper.createUser(user);
        return (id > 0);
    }

    @Override
    public User getByToken(String token) {
        String userStr = jedisUtils.get(token, RedisDBEnum.USER_DB.getDb());
        if (StringUtils.isEmpty(userStr)){
            return null;
        }
        User user = JSON.parseObject(userStr, User.class);
        return user;
    }

    /**
     * 登录验证添加到cookie和redis中
     * @param response
     * @param uuid
     * @param cookieStr
     * @param user
     */
    public void addCookieAndRedis(HttpServletResponse response, String uuid, String cookieStr, User user){
        Cookie cookie = new Cookie(TOKEN_NAME, uuid);
        cookie.setMaxAge(USER_MAX_COOKIE_TIME);
        cookie.setPath("/");
        response.addCookie(cookie);
        jedisUtils.setex(cookieStr, JSON.toJSONString(user), USER_MAX_COOKIE_TIME, RedisDBEnum.USER_DB.getDb());
    }

}
