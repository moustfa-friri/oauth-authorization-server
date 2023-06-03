package com.baeldung.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class authController {

    @GetMapping("/verifyOtp")
    public ModelAndView verifyOtp(ModelAndView modelAndView){
        modelAndView.setViewName("verifyOtp");
        return modelAndView;
    }
}
