package cn.dai.seckill.type;

/**
 * @author adrian
 * @date 2019/1/7 10:55
 **/
public enum RedisDBEnum {

    USER_DB(0),GOODS_DB(0),ACCESS_BD(0);

    private RedisDBEnum(Integer db){
        this.db = db;
    }

    private Integer db;

    public Integer getDb() {
        return db;
    }

    public void setDb(Integer db) {
        this.db = db;
    }
}
