package com.zane.spring.framework.beans;

import java.util.regex.PatternSyntaxException;

public class ZBeanWrapper {
    public ZBeanWrapper(Object wraapedInstance) {
        this.wrappedClass = wraapedInstance.getClass();
        this.wraapedInstance = wraapedInstance;
    }

    public Object getWraapedInstance() {
        return wraapedInstance;
    }

    public Class<?> getWrappedClass() {
        return wrappedClass;
    }

    private Object wraapedInstance;
    private Class<?> wrappedClass;
}
