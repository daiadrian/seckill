package cn.dai.seckill.result;

/**
 * @author adrian
 */
public enum  CodeMsg {
    LOGIN_FAIL("登录失败"),
    LOGIN_SUCCESS("登录成功"),
    PASSWORD_ERROR("密码或用户名错误"),
    GOOD_ID_FAIL("没有此商品");

    private String msg;
    private CodeMsg(String msg){
        this.msg = msg;
    }
    public String getMsg(){
        return msg;
    }
}
