package com.alejo.appsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping(path = "/loans")
public class LoansController {

    @GetMapping
    public Map<String,String> getLoans(){
        return Collections.singletonMap("message","Welcome to loans controller");
    }
}
