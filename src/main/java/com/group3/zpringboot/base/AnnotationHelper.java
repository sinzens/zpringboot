package com.group3.zpringboot.base;

import com.sun.istack.internal.NotNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;

public class AnnotationHelper {
    public static <T> boolean hasAnnotation(@NotNull T object, @NotNull Class<? extends Annotation> annotationClass) {
        return object.getClass().isAnnotationPresent(annotationClass);
    }

    public static <T, A extends Annotation> A getAnnotation(@NotNull T object, @NotNull Class<A> annotationClass) {
        return object.getClass().getAnnotation(annotationClass);
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
}
