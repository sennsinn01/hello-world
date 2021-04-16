package com.zane.spring.framework.core;

import java.lang.reflect.Type;

public interface ZBeanfactory {

    Object getBean(String name);

    Object getBean(Class<?> clazz);

}
