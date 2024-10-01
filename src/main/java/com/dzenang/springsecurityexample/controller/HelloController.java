package com.dzenang.springsecurityexample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hellohello";
    }

    @GetMapping
    public String index() {
        return "index";
    }
}
