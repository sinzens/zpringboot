package com.group3.zpring.bean;

import com.group3.zpring.base.AnnotationHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BeanParser {
    public static List<BeanClass> parse(String className) {
        try {
            Class<?> beanClass = Class.forName(className);
            return getIdentifiers(beanClass)
                    .stream()
                    .map(identifier -> new BeanClass(identifier, beanClass))
                    .collect(Collectors.toList());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private static List<String> getIdentifiers(Class<?> beanClass) {
        List<String> identifiers = new ArrayList<>();

        if (AnnotationHelper.isController(beanClass)) {
            identifiers.add(beanClass.getName());
        }

        if (AnnotationHelper.isService(beanClass)) {
            identifiers.add(beanClass.getName());
        }

        if (AnnotationHelper.isComponent(beanClass)) {
            Component component = AnnotationHelper.getAnnotation(beanClass, Component.class);
            String value = component.value();
            if (!value.isEmpty()) {
                identifiers.add(value);
            } else {
                Class<?>[] interfaces = beanClass.getInterfaces();
                identifiers.addAll(Arrays.stream(interfaces).map(Class::getName).collect(Collectors.toList()));
            }
        }

        return identifiers;
    }
}
