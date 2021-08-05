package com.group3.zpring.app;

public class ZpringApplication {
    public static <T> void run(Class<T> applicationClass, String[] args) {
        processCommandLineArguments(args);
    }

    public static void processCommandLineArguments(String[] args) {
        for (String arg : args) {
            System.out.printf("Processing command line argument: %s%n", arg);
        }
    }
}
