package com.contactmanager.controller;

import java.security.Principal;

import com.contactmanager.dao.userrepo;
import com.contactmanager.entities.Contact;
import com.contactmanager.entities.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private userrepo userRepository;

    @RequestMapping("/dashboard")
    public String dashboard(Model model, Principal principal){
        String userName = principal.getName();
        model.addAttribute("title", "Dashboard - Contact Manager");
        User user = userRepository.getUserByUserName(userName);
        model.addAttribute("user", user);
        return "normal/user_dashboard";
    }

    @RequestMapping("/add-contact")
    public String add_contact(Model model, Principal principal){
        String userName = principal.getName();
        model.addAttribute("title", "Add Contact - Contact Manager");
        model.addAttribute("contact", new Contact());
        User user = userRepository.getUserByUserName(userName);
        model.addAttribute("user", user);
        return "normal/user_add_contact";
    }
    @PostMapping("/process-new-contact")
    public String process_new_contact(@ModelAttribute Contact contact, Model model,  Principal principal){
        model.addAttribute("title", "Add Contact - Contact Manager");
        String userName = principal.getName(); 
        User user = userRepository.getUserByUserName(userName);
        model.addAttribute("user", user);

        user.getContacts().add(contact);
        this.userRepository.save(user);
        return "normal/user_add_contact";
    }
}