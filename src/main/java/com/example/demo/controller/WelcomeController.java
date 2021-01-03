package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/welcome")
public class WelcomeController {

    @GetMapping("showWelcomePage")
    public String showWelcomePage(){


        return "welcome/welcome-user";

    }


}
