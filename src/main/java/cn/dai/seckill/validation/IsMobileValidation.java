package cn.dai.seckill.validation;

import cn.dai.seckill.util.ValidatorUtil;
import org.springframework.validation.ValidationUtils;
import org.thymeleaf.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author adrian
 * @date 2018/10/27 14:24
 **/
public class IsMobileValidation implements ConstraintValidator<IsMobile, String> {

    private boolean required = false;

    @Override
    public void initialize(IsMobile constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (required){
            return ValidatorUtil.isMobile(value);
        }else {
            if (StringUtils.isEmpty(value)){
                return true;
            }else {
                return ValidatorUtil.isMobile(value);
            }
        }
    }
}
