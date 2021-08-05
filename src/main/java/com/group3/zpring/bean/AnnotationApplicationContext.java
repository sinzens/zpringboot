package com.group3.zpring.bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class AnnotationApplicationContext implements ApplicationContext, BeanFactory {

    private final Map<String, Object> entityMap;

    private final List<BeanClass> beans;

    private final Properties config;

    public AnnotationApplicationContext(String fileName) {
        entityMap = new HashMap<>();
        beans = new ArrayList<>();
        config = new Properties();

        InputStream input;
        input = this.getClass().getClassLoader().getResourceAsStream(fileName);

        try {
            config.load(input);
            this.register();
            this.createBeans();
            this.inject();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                Objects.requireNonNull(input).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Object getBean(String name) {
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getBean(String name, Class<T> beanClass) {
        return (T)this.entityMap.get(name);
    }

    @Override
    public Map<String, Object> getAllBeans() {
        return this.entityMap;
    }

    @Override
    public void registerBean(List<BeanClass> beans) {
        this.beans.addAll(beans);
    }

    @Override
    public void registerEntity(String name, Object entity) {
        this.entityMap.put(name, entity);
    }

    private void inject() {
        new DependencyInjector().inject(this.entityMap);
    }

    private void createBeans() {
        new BeanCreator(this).create(this.beans);
    }

    private void register() {
        new BeanScanner(this).scan(this.config);
    }
}
