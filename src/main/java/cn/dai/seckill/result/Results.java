package cn.dai.seckill.result;

/**
 * @author adrian
 * @date 2018/10/27 10:42
 **/
public class Results<T> {

    private String msg;
    private Integer status;
    private T data;

    /**
     * 请求成功
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Results<T> success(T data){
        return new Results<T>("请求成功", StatusCode.SUCCESS.value(), data);
    }

    public static <T> Results<T> success(Integer code){
        return new Results<T>("请求成功", code);
    }

    public static <T> Results<T> success(String msg, Integer code){
        return new Results<T>(msg, code);
    }

    public static <T> Results<T> success(String msg){
        return new Results<T>(msg, StatusCode.SUCCESS.value());
    }

    /**
     * 请求失败
     * @param faild 失败码
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Results<T> faild(Integer faild, T data){
        return new Results<T>("请求失败", faild, data);
    }

    public static <T> Results<T> faild(T data){
        return new Results<T>("请求失败", StatusCode.FAILD.value(), data);
    }

    public static <T> Results<T> faild(String msg, Integer faild){
        return new Results<T>(msg, faild);
    }

    public static <T> Results<T> faild(String msg){
        return new Results<T>(msg, StatusCode.FAILD.value());
    }

    public Results(String msg, Integer status) {
        this.msg = msg;
        this.status = status;
    }

    public Results(String msg, Integer status, T data) {
        this.msg = msg;
        this.status = status;
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
