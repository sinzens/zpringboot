package com.group3.zpring.bean;

import com.group3.zpring.base.AnnotationHelper;
import com.group3.zpring.base.FileHelper;

import java.lang.reflect.Field;
import java.util.Map;

public class DependencyInjector {
    public void inject(Map<String, Object> entityMap) {
        if (!entityMap.isEmpty()) {
            this.injectEachEntity(entityMap);
        }
    }

    private void injectEachEntity(Map<String, Object> entityMap) {
        entityMap.forEach((name, entity) -> {
            Field[] fields = entity.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (!AnnotationHelper.hasAnnotation(field, Autowire.class)) {
                    continue;
                }

                String identifier = this.getFieldAutowireIdentifier(field);
                field.setAccessible(true);

                try {
                    field.set(entity, entityMap.get(identifier));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private String getFieldAutowireIdentifier(Field field) {
        Autowire autowire = AnnotationHelper.getAnnotation(field, Autowire.class);
        String identifier = autowire.value();

        return identifier.isEmpty()
            ? field.getType().getName()
            : FileHelper.joinIdentifierWithPackageName(identifier, field.getType());
    }
}
