package com.zane.spring.framework.annotation;

import java.lang.annotation.*;

@Documented
@Target(value = ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ZService {
    String value() default "";
}

