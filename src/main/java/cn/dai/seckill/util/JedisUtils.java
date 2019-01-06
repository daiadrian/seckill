package cn.dai.seckill.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

/**
 * @author adrian
 * @date 2019/1/5 21:20
 **/
@Component
public class JedisUtils {

    @Autowired
    private Jedis jedis;

    /**
     * 根据key获取值
     * @param key
     * @param db
     * @return
     */
    public String get(String key, Integer db){
        String str = null;
        try {
            jedis.select(db);
            str = jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
            //TODO 日志
        }
        return str;
    }

    /**
     *
     * @param key
     * @param value
     * @param db
     * @return
     */
    public String set(String key, String value, Integer db){
        String str = null;
        try {
            jedis.select(db);
            str = jedis.set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            //TODO 日志
        }
        return str;
    }

    /**
     *
     * @param key
     * @param second
     * @param db
     * @return
     */
    public boolean expire(String key, int second, Integer db){
        try {
            jedis.select(db);
            Boolean exists = jedis.exists(key);
            if (exists != null && exists)
                jedis.expire(key, second);
            else
                return false;
        } catch (Exception e) {
            e.printStackTrace();
            //TODO 日志
        }
        return true;
    }

}
