package com.group3.zpring.base;

public class FileHelper {
    public static String joinPackageDirectories(String parentDir, String dir) {
        return String.format("%s.%s", parentDir, dir);
    }

    public static String joinIdentifierWithPackageName(String identifier, Class<?> objectClass) {
        return String.format("%s.%s", objectClass.getPackage().getName(), identifier);
    }
}
