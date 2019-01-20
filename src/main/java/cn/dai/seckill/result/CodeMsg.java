package cn.dai.seckill.result;

/**
 * @author adrian
 */
public enum  CodeMsg {
    LOGIN_FAILD("登录失败", 501001),
    MOBILE_NOT_EXIEST("手机号码不存在", 501002),
    MOBILE_EXIEST("手机号码已存在", 501003),
    PASSWORD_ERROR("密码或用户名错误", 501004),
    LOGIN_SUCCESS("登录成功", 200),
    REGISTER_SUCCESS("注册成功", 200),
    REGISTER_FAILD("注册失败", 501005),
    ACCESS_LIMIT_REACHED("访问太频繁!", 501006),
    GOOD_ID_FAIL("没有此商品", 502001);

    private String msg;
    private Integer status;
    private CodeMsg(String msg, Integer status){
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
