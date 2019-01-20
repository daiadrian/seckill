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
     * 将值 value 关联到 key ，并将 key 的生存时间设为 seconds (以秒为单位)
     * @param key
     * @param value
     * @param db
     * @return
     */
    public String setex(String key, String value, int seconds, Integer db){
        String str = null;
        try {
            jedis.select(db);
            str = jedis.setex(key, seconds, value);
        } catch (Exception e) {
            e.printStackTrace();
            //TODO 日志
        }
        return str;
    }

    /**
     * 将 key 的值设为 value ，当且仅当 key 不存在。
     * 若给定的 key 已经存在，则 SETNX 不做任何动作。
     *
     * 设置成功，返回 1 。
     * 设置失败，返回 0 。
     * @param key
     * @param value
     * @param db
     * @return
     */
    public Long setnx(String key, String value, Integer db){
        Long setnx = 0L;
        try {
            jedis.select(db);
            setnx = jedis.setnx(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            //TODO 日志
        }
        return setnx;
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


    /**
     *
     * @param key
     * @param db
     * @return
     */
    public Long incr(String key, Integer db){
        Long incr = null;
        try {
            jedis.select(db);
            incr = jedis.incr(key);
        } catch (Exception e) {
            e.printStackTrace();
            //TODO 日志
        }
        return incr;
    }

}
