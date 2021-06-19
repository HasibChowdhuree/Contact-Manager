package com.contactmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {

    @RequestMapping("/")
    public String Home(Model model){
        model.addAttribute("title", "Home - Contact Manager");
        return "home";
    }
    
}