package com.zane.spring.framework.context;

import com.zane.spring.framework.beans.ZBeanWrapper;
import com.zane.spring.framework.beans.config.ZBeanDefinition;
import com.zane.spring.framework.beans.support.ZBeanDefinitionReader;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ZApplicationContext {

    protected final Map<String, ZBeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    private String [] configLocations;

    private ZBeanDefinitionReader reader;
    // 单例的IOC容器缓存
    private Map<String,Object> factoryBeanObjectCache= new ConcurrentHashMap<String,Object>();
    // 通用的IOC容器
    private Map<String, ZBeanWrapper> factoryBeanInstanceCache= new ConcurrentHashMap<String,ZBeanWrapper>();

    public Object getBean(String beanName) {
        ZBeanDefinition beanDefinition = this.beanDefinitionMap.get(beanName);
        initializeBean(beanName,beanDefinition);
        return null;
    }

    private Object initializeBean(String beanName, ZBeanDefinition beanDefinition) {
        String beanClassName = beanDefinition.getBeanClassName();
        try {
            Class<?> aClass = Class.forName(beanClassName);
            Object bean = aClass.newInstance();
            ZBeanWrapper zBeanWrapper = new ZBeanWrapper(bean);

            populateBean(zBeanWrapper);

            return zBeanWrapper.getWraapedInstance();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void populateBean(ZBeanWrapper zBeanWrapper) {
    }
}
