package com.group3.zpring.bean;

import java.util.List;

public class BeanCreator {
    private final BeanFactory factory;

    public BeanCreator(BeanFactory factory) {
        this.factory = factory;
    }

    public void create(List<BeanClass> beans) {
        for (BeanClass bean : beans) {
            Object entity = bean.getEntity();
            this.factory.registerEntity(bean.getIdentifier(), entity);
        }
    }
}
