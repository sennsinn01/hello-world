package com.zane.spring.framework.annotation;

import java.lang.annotation.*;

@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface ZRequestParam {
    String value() default "";
}
