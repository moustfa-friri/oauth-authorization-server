package com.baeldung.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class API {

    @GetMapping("/test")
    public String test(){
        return "hi";
    }
}
