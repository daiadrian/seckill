package cn.dai.seckill.service;

import cn.dai.seckill.entity.User;
import cn.dai.seckill.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author adrian
 * @date 2018/10/27 14:13
 **/
@Service
public class LoginService {

    @Autowired
    private UserMapper userMapper;

    public boolean login(User user){
        User userByMobileAndPassword = userMapper.getUserByMobileAndPassword(user);
        if (userByMobileAndPassword != null){
            return true;
        }
        return false;
    }


}
