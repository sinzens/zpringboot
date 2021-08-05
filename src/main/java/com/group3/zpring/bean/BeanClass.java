package com.group3.zpring.bean;

public class BeanClass {
    private String identifier;

    private Class<?> beanClass;

    public BeanClass(String identifier, Class<?> beanClass) {
        this.identifier = identifier;
        this.beanClass = beanClass;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public void setBeanClass(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    public Class<?> getBeanClass() {
        return this.beanClass;
    }

    public Object getEntity() {
        try {
            return this.beanClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
