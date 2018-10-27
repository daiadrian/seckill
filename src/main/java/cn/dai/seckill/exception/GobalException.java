package cn.dai.seckill.exception;

/**
 * @author adrian
 * @date 2018/10/27 11:14
 **/
public class GobalException extends Exception {

    private String msg;

    public GobalException(String msg){
        super(msg);
        this.msg = msg;
    }

    public String getMsg(){
        return msg;
    }

}
