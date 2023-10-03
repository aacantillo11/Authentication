package com.alejo.appsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping(path = "/cards")
public class CardController {

    @GetMapping
    public Map<String,String> getCards(){
        return Collections.singletonMap("message","Welcome to card controller");
    }
}
