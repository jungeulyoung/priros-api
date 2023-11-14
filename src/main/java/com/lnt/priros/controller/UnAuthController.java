package com.lnt.priros.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/unauth")
public class UnAuthController {


    @GetMapping("/test")
    public String test() {
        return "OK";
    }
    
}
