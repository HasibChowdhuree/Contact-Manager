package com.contactmanager.controller;

import com.contactmanager.dao.userrepo;
import com.contactmanager.entities.Contact;
import com.contactmanager.entities.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @Autowired
    private userrepo userrep;

    @GetMapping("/test")
    @ResponseBody
    public String test(){
        User user = new User();
        user.setName("Hasib Chowdhuree");
        user.setEmail("hasibchowdhuree@icloud.com");

        Contact contact = new Contact();
        user.getContacts().add(contact);

        userrep.save(user);
        System.out.println("Hasib");
        return "Hello World!";
    }
}