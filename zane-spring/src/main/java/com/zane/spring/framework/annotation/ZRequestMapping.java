package com.zane.spring.framework.annotation;

import java.lang.annotation.*;

@Documented
@Target(value={ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ZRequestMapping {
    String value() default "";
}
