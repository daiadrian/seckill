package cn.dai.seckill.result;

/**
 * @author adrian
 */
public enum  CodeMsg {
    LOGIN_FAIL("登录失败", 501001),
    MOBILE_NOT_EXIEST("手机号码不存在", 501002),
    PASSWORD_ERROR("密码或用户名错误", 501003),
    LOGIN_SUCCESS("登录成功", 200),
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
