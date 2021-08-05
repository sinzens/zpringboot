package com.group3.zpringtest.mock;

import com.group3.zpring.bean.Service;

@Service
public class TestService {
    public void test(String str) {
        System.out.printf("Service prints: %s%n", str);
    }
}
