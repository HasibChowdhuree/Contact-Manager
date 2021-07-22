package com.contactmanager.controller;

import java.security.Principal;

import com.contactmanager.dao.userrepo;
import com.contactmanager.entities.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private userrepo userRepository;

    @RequestMapping("/dashboard")
    public String dashboard(Model model, Principal principal){
        String userName = principal.getName();

        User user = userRepository.getUserByUserName(userName);
        model.addAttribute("user", user);
        return "normal/user_dashboard";
    }
}