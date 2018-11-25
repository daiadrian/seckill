package cn.dai.seckill.exception;

/**
 * @author adrian
 * @date 2018/10/27 11:14
 **/
public class GobalException extends RuntimeException {

    private String msg;
    private Integer status;

    public GobalException(String msg, Integer status){
        super(msg);
        this.msg = msg;
        this.status = status;
    }

    public String getMsg(){
        return msg;
    }

    public Integer getStatus() {
        return status;
    }
}
