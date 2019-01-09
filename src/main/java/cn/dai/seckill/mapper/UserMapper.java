package cn.dai.seckill.mapper;

import cn.dai.seckill.entity.User;
import org.apache.ibatis.annotations.*;

/**
 * @author adrian
 * @date 2018/10/27 14:13
 **/
@Mapper
public interface UserMapper {

    @Select("select u.id,u.mobile,u.password,u.salt,u.register_date,u.last_login_date,u.login_count,u.create_date,u.update_date " +
            "from user u where mobile = #{id}")
    public User getByMobile(@Param("id") String mobile);

    @Insert("insert into user(mobile,password,salt,register_date,last_login_date,login_count,create_date,update_date) " +
            "values(#{mobile},#{password},#{salt},#{registerDate},#{lastLoginDate},#{loginCount},#{createDate},#{updateDate})")
    @SelectKey(keyColumn="id", keyProperty="id", resultType=long.class, before=false, statement="select last_insert_id()")
    public long createUser(User user);

    @Update("update user set login_count = #{newCount}, update_date= #{user.updateDate}, last_login_date= #{user.lastLoginDate} " +
            "where id = #{user.id} and login_count = #{user.loginCount}")
    public int userLoginIn(@Param("user") User user, @Param("newCount") int newCount);

}
