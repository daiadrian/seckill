package cn.dai.seckill.result;

/**
 * @author adrian
 * @date 2018/10/27 10:49
 **/
public enum StatusCode {
    SUCCESS(200),
    NO_FOUND(404),
    FAILD(500);

    private Integer code;

    private StatusCode(Integer code){
        this.code = code;
    }

    public Integer value(){
        return code;
    }

}
