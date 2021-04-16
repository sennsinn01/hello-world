package com.zane.spring.framework.annotation;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ZAutoWired {
    String value() default "";
}
