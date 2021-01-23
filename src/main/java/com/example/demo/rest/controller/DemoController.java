package com.example.demo.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/")
    public String test() {
        return "Well done";
    }

    @GetMapping("/")
    public String getText(@RequestAttribute("text") String text) {
        return text;
    }
}
