package com.contactmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home( Model model){
        model.addAttribute("title", "Home - Contact Manager");
        return "home";
    }
    
    @RequestMapping("/register")
    public String register( Model model){
        model.addAttribute("title", "Register - Contact Manager");
        return "register";
    }

    @RequestMapping("/login")
    public String login( Model model){
        model.addAttribute("title", "Login - Contact Manager");
        return "login";
    }
    
}
