package com.contactmanager.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.contactmanager.dao.userrepo;
import com.contactmanager.entities.User;
import com.contactmanager.helper.Message;

@Controller
public class HomeController {
    
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private userrepo userRepository;

    @RequestMapping("/")
    public String Home(Model model){
        model.addAttribute("title", "Home - Contact Manager");
        return "home";
    }
	@RequestMapping(path="/processsignup", method=RequestMethod.POST)
	private String ProcessSignup(@RequestParam("email") String email,
			@RequestParam("name") String name,
			@RequestParam("password") String password,
			@RequestParam("confirmpassword") String cpassword,Model model,
			HttpSession session) {
		User user = new User();
		user.setEmail(email);
		user.setName(name);
		try {
			if(userRepository.findByEmail(email)!=null) {
				throw new Exception("user email already exists");
			}
			if(password.length()<6) {
				throw new Exception("password length must be at least 6");
			}
			if(!password.equals(cpassword)) {
				System.out.println(password+" "+cpassword);
				System.out.println("password did not match");
				throw new Exception("password did not match");
			}
			user.setPassword(passwordEncoder.encode(password));
			user.setRole("ROLE_USER");
			this.userRepository.save(user);
			model.addAttribute("user",user);
			session.setAttribute("message",new Message("Successfully registered! ","alert-success"));
			return "home";
		}
		catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("user",user);
			session.setAttribute("message",new Message(e.getMessage(),"alert-danger"));
			return "home";
		}
	}
    
}