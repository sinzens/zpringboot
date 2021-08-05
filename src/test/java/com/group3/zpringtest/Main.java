package com.group3.zpringtest;

import com.group3.zpring.app.Application;
import com.group3.zpring.app.ZpringApplication;
import com.group3.zpring.bean.AnnotationApplicationContext;
import com.group3.zpring.bean.ApplicationContext;
import com.group3.zpringtest.mock.TestController;

@Application
public class Main {
    public static void main(String[] args) {
        ZpringApplication.run(Main.class, args);
        try {
            ApplicationContext context = new AnnotationApplicationContext("application.properties");
            TestController controller = context.getBean("com.group3.zpringtest.mock.TestController", TestController.class);
            controller.test();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
