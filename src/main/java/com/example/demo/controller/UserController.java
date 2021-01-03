package com.example.demo.controller;


import com.example.demo.entity.User;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/showCreateUserPage")
    public String showCreateUserPage(Model model){

        User user = new User();

        model.addAttribute("user", user);

        return "users/create-user";
    }


    @GetMapping("/login")
    public String loginUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){

            return "login-user";
        }else{


            return "welcome/welcome-user";
        }

    }

    @GetMapping("/access-denied")
    public String accessDenied(){
        return "access-denied";
    }

}
