package com.group3.zpringtest.mock;

import com.group3.zpring.bean.Autowire;
import com.group3.zpring.bean.Controller;

@Controller
public class TestController {
    @Autowire
    private TestService service;

    public void test() {
        service.test("This is a test string");
    }
}
