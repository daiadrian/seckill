package cn.dai.seckill.util;

import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author adrian
 * @date 2018/10/27 11:
 **/
public class ValidatorUtil {

    private static final Pattern mobile_pattern = Pattern.compile("1\\d{10}");
	
	public static boolean isMobile(String src) {
		if(StringUtils.isEmpty(src)) {
			return false;
		}
		Matcher m = mobile_pattern.matcher(src);
		return m.matches();
	}
	
}