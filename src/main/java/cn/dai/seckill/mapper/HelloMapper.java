package cn.dai.seckill.mapper;

import cn.dai.seckill.entity.Hello;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author adrian
 * @date 2018/10/27 11:22
 **/
@Mapper
public interface HelloMapper {

    @Select("SELECT * FROM hello WHERE id=#{id}")
    public Hello findHelloById(@Param("id") Integer id);

}
