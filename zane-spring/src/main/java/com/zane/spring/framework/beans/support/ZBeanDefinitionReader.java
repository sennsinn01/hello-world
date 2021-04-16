package com.zane.spring.framework.beans.support;

import com.zane.spring.framework.beans.config.ZBeanDefinition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ZBeanDefinitionReader {
    private List<String> registyBeanClasses = new ArrayList<String>();
    private Properties config = new Properties();
    private final String scanKey = "scanPackage";

    public ZBeanDefinitionReader(String ... locations) {
        try(InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(locations[0])){
            config.load(resourceAsStream);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(),e);
        }
        doScan(config.getProperty(scanKey));

    }

    private void doScan(String packagePath) {
        URL resource = this.getClass().getClassLoader().getResource(packagePath.replaceAll("\\.","/"));
        File file = new File(resource.getFile());
        for (File listFile : file.listFiles()) {
            if (listFile.isDirectory()) {
                doScan(packagePath + "." + listFile.getName());
            } else {
                if (listFile.getName().endsWith("class")) {
                    String className = packagePath + "." + listFile.getName().replace(".class","");
                    registyBeanClasses.add(className);
                }
                continue;
            }
        }
    }

    public List<ZBeanDefinition> loadBeanDefinistions() throws ClassNotFoundException {
        List<ZBeanDefinition> result = new ArrayList<>();
        for (String registyBeanClass : this.registyBeanClasses) {
            Class<?> aClass = Class.forName(registyBeanClass);
            if (aClass.isInterface()) {
                continue;
            }
            result.add(doCreateBeanDefinition(toLowerFirst(aClass.getSimpleName()),aClass.getName()));
            Class<?>[] interfaces = aClass.getInterfaces();
            for (Class<?> anInterface : interfaces) {
                result.add(doCreateBeanDefinition(anInterface.getName(),aClass.getName()));
            }
        }
        return result;
    }

    private ZBeanDefinition doCreateBeanDefinition(String beanName, String name) {
        ZBeanDefinition beanDefinition = new ZBeanDefinition();
        beanDefinition.setFactoryBeanName(beanName);
        beanDefinition.setBeanClassName(name);
        return beanDefinition;
    }

    private String toLowerFirst(String simpleName) {
        char[] chars = simpleName.toCharArray();
        chars[0]+=32;
        return new String(chars);
    }
}
