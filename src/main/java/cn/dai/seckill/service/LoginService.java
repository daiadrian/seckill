package cn.dai.seckill.service;

import cn.dai.seckill.entity.User;
import cn.dai.seckill.exception.GobalException;
import cn.dai.seckill.mapper.UserMapper;
import cn.dai.seckill.result.CodeMsg;
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
public interface LoginService {

    public boolean login(HttpServletResponse response, User user);

    public void addCookie(HttpServletResponse response, String uuid);

}
