package com.group3.zpring.bean;

import com.group3.zpring.base.FileHelper;
import com.group3.zpring.exception.ClassFileNotFoundException;
import com.group3.zpring.exception.NullPackageNameException;
import com.group3.zpring.exception.NullScanPackageUrlException;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

public class BeanScanner {

    private final BeanFactory factory;

    public static final String SCAN_PACKAGE = "package";

    public BeanScanner(BeanFactory factory) {
        this.factory = factory;
    }

    public void scan(Properties properties) {
        try {
            this.registerBeans(properties.getProperty(SCAN_PACKAGE));
        } catch (NullScanPackageUrlException | NullPackageNameException | ClassFileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void registerBeans(String packageName) throws NullScanPackageUrlException, NullPackageNameException, ClassFileNotFoundException {
        if (packageName == null) {
            throw new NullPackageNameException(String.format("Failed to find property key %s", SCAN_PACKAGE));
        }

        String resourceLocation = String.format("./%s", packageName.replaceAll("\\.","/"));
        URL url = this.getClass()
            .getClassLoader()
            .getResource(resourceLocation);

        if (url == null) {
            throw new NullScanPackageUrlException(String.format("Failed to load resource at %s", resourceLocation));
        }

        File directory = new File(url.getFile().replaceAll("%20", " "));
        if (!directory.exists() || !directory.isDirectory()) {
            throw new ClassFileNotFoundException(String.format("Failed to find class files at %s", directory.getAbsolutePath()));
        }

        for (File file : Objects.requireNonNull(directory.listFiles())) {
            if (file.isDirectory()) {
                this.registerBeans(FileHelper.joinPackageDirectories(packageName, file.getName()));
            } else {
                String cleanedFileName = file.getName().replaceAll(".class","").trim();
                String className = FileHelper.joinPackageDirectories(packageName, cleanedFileName);

                List<BeanClass> beans = BeanParser.parse(className);
                if (!beans.isEmpty()) {
                    this.factory.registerBean(beans);
                }
            }
        }
    }
}
