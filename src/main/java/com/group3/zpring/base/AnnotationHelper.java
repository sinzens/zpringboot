package com.group3.zpring.base;

import com.group3.zpring.bean.Component;
import com.group3.zpring.bean.Controller;
import com.group3.zpring.bean.Service;
import com.sun.istack.internal.NotNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class AnnotationHelper {
    public static <T> boolean hasAnnotation(@NotNull T object, @NotNull Class<? extends Annotation> annotationClass) {
        return object.getClass().isAnnotationPresent(annotationClass);
    }

    public static boolean hasAnnotation(Class<?> objectClass, @NotNull Class<? extends Annotation> annotationClass) {
        return objectClass.isAnnotationPresent(annotationClass);
    }

    public static boolean hasAnnotation(Field field, @NotNull Class<? extends Annotation> annotationClass) {
        return field.isAnnotationPresent(annotationClass);
    }

    public static <T, A extends Annotation> A getAnnotation(@NotNull T object, @NotNull Class<A> annotationClass) {
        return object.getClass().getAnnotation(annotationClass);
    }

    public static <A extends Annotation> A getAnnotation(Class<?> objectClass, @NotNull Class<A> annotationClass) {
        return objectClass.getAnnotation(annotationClass);
    }

    public static <A extends Annotation> A getAnnotation(Field field, @NotNull Class<A> annotationClass) {
        return field.getAnnotation(annotationClass);
    }

    public static <A extends Annotation> Object getProperty(String property, A annotation) {
        try {
            try {
                return annotation.getClass().getMethod(property).invoke(annotation);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isController(Class<?> objectClass) {
        return objectClass.isAnnotationPresent(Controller.class);
    }

    public static boolean isController(Object object) {
        return object.getClass().isAnnotationPresent(Controller.class);
    }

    public static boolean isService(Class<?> objectClass) {
        return objectClass.isAnnotationPresent(Service.class);
    }

    public static boolean isService(Object object) {
        return object.getClass().isAnnotationPresent(Service.class);
    }

    public static boolean isComponent(Class<?> objectClass) {
        return objectClass.isAnnotationPresent(Component.class);
    }

    public static boolean isComponent(Object object) {
        return object.getClass().isAnnotationPresent(Component.class);
    }
}
