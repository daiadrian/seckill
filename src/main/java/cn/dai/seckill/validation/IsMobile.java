package cn.dai.seckill.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

/**
 * @author adrian
 * @date 2018/10/27 14:21
 **/
@Documented
@Target({METHOD, ANNOTATION_TYPE, FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsMobileValidation.class)
public @interface IsMobile {

    boolean required() default true;

    String message() default "手机号码格式错误";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
