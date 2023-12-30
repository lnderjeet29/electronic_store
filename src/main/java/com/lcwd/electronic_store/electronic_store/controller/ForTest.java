package com.lcwd.electronic_store.electronic_store.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class ForTest {
    @GetMapping("method")
    public String call(){
        return "mera naam jeet";
    }
}
