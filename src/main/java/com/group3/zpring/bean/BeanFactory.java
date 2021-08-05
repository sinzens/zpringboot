package com.group3.zpring.bean;

import java.util.List;

public interface BeanFactory {
    void registerBean(List<BeanClass> beans);

    void registerEntity(String name, Object entity);
}
