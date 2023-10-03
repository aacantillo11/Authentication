package com.alejo.appsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/about_us")
public class AboutUsController {

    @GetMapping
    public Map<String,String> getAboutUs(){
        return Collections.singletonMap("message","Welcome to about us controller");
    }
}
