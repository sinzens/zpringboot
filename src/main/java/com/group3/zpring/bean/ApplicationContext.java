package com.group3.zpring.bean;

import java.util.Map;

public interface ApplicationContext {
    Object getBean(String name);

    <T> T getBean(String name, Class<T> beanClass);

    Map<String, Object> getAllBeans();
}
