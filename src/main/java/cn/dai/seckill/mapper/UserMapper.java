package cn.dai.seckill.mapper;

import cn.dai.seckill.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author adrian
 * @date 2018/10/27 14:13
 **/
@Mapper
public interface UserMapper {

    @Select("SELECT u.* FROM user WHERE mobile=#{mobile} AND password=#{password}")
    public User getUserByMobileAndPassword(User user);

}
