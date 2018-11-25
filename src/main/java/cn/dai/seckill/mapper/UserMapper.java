package cn.dai.seckill.mapper;

import cn.dai.seckill.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author adrian
 * @date 2018/10/27 14:13
 **/
@Mapper
public interface UserMapper {

    @Select("select u.id,u.mobile,u.password,u.salt,u.registerDate,u.lastLoginDate,u.loginCount,u.createDate,u.updateDate " +
            "from user u where mobile = #{id}")
    public User getByMobile(@Param("id")String mobile);

}
