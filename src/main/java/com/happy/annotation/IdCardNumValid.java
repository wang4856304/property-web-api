package com.happy.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wangjun
 * @Title: IdCardNumValid
 * @ProjectName newHappy
 * @Description: TODO
 * @date 2018/10/18 18:10
 */

@Target(value= {ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=IdCardNumValidator.class)
public @interface IdCardNumValid {
    String message() default "身份证格式不合法";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
