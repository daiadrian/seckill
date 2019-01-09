package cn.dai.seckill.util;

import org.springframework.util.DigestUtils;

/**
 * @author adrian
 * @date 2018/11/25 10:53
 **/
public class MD5Util {

    public static final String slat = "GithubDH";

    public static String md5(String password){
        String str = slat.charAt(0) + slat.charAt(5) + password + slat.charAt(0) + slat.charAt(3);
        return DigestUtils.md5DigestAsHex(str.getBytes());
    }

    public static String md5(String password, String dbSalt){
        String str = dbSalt.charAt(0) + dbSalt.charAt(5) + password + dbSalt.charAt(0) + dbSalt.charAt(3);
        return DigestUtils.md5DigestAsHex(str.getBytes());
    }

}
