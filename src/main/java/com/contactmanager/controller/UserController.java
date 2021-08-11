package com.contactmanager.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import com.contactmanager.dao.userrepo;
import com.contactmanager.entities.Contact;
import com.contactmanager.entities.User;
import com.contactmanager.helper.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private userrepo userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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
    public String process_new_contact(@ModelAttribute Contact contact, Model model,  Principal principal, HttpSession session){
        model.addAttribute("title", "Add Contact - Contact Manager");
        String userName = principal.getName(); 
        User user = userRepository.getUserByUserName(userName);
        model.addAttribute("user", user);

        try{
            user.getContacts().add(contact);
            this.userRepository.save(user);
			session.setAttribute("message",new Message("Successfully added! ","alert-success"));
            return "normal/user_add_contact";
        }
        catch(Exception e) {
			e.printStackTrace();
			session.setAttribute("message",new Message(e.getMessage(),"alert-danger"));
			return "normal/user_add_contact";
		}
    }
    @GetMapping("/change-password")
    public String change_password(Model model, Principal principal) {
        model.addAttribute("title", "Change Password - Contact Manager");
    	String userName = principal.getName(); 
        User user = userRepository.getUserByUserName(userName);
        model.addAttribute("user", user);
    	return "normal/user_change_password";
    }
    @PostMapping("/process-change-password")
    public String process_change_password(Model model, Principal principal, @RequestParam("oldPassword") String oldPassword, @RequestParam("password") String password, @RequestParam("confirmPassword") String confirmPassword, HttpSession session ){
        model.addAttribute("title", "Change Password - Contact Manager");
    	String userName = principal.getName(); 
        User user = userRepository.getUserByUserName(userName);
        model.addAttribute("user", user);
        try{
            if(password.length()<6) {
				throw new Exception("Password length must be at least 6");
			}
			if(!password.equals(confirmPassword)) {
				throw new Exception("Passwords did not match");
			}
            if(this.bCryptPasswordEncoder.matches(oldPassword, user.getPassword())){
                user.setPassword(this.bCryptPasswordEncoder.encode(password));
                this.userRepository.save(user);
                session.setAttribute("message",new Message("Password Changed! ","alert-success"));
            }
            else{
                throw new Exception("Enter correct Old Password");
            }
            return "normal/user_change_password";
        }
        catch(Exception e){
			e.printStackTrace();
			session.setAttribute("message",new Message(e.getMessage(),"alert-danger"));
            return "normal/user_change_password";
        }
    }
    @GetMapping("/change-email")
    public String change_username(Model model, Principal principal) {
        model.addAttribute("title", "Change Email - Contact Manager");
    	String userName = principal.getName(); 
        User user = userRepository.getUserByUserName(userName);
        model.addAttribute("user", user);
    	return "normal/user_change_email";
    }
    @PostMapping("/process-change-email")
    public String process_change_username(Model model, Principal principal, @RequestParam("email") String newemail, HttpSession session ){
        model.addAttribute("title", "Change Email - Contact Manager");
    	String userName = principal.getName(); 
        User user = userRepository.getUserByUserName(userName);
        model.addAttribute("user", user);
        try{
			if(userRepository.findByEmail(newemail)!=null) {
				throw new Exception("Email already exits");
			}
            else{
                user.setEmail(newemail);
                this.userRepository.save(user);
                session.setAttribute("message",new Message("Email Changed! ","alert-success"));
            }
            return "normal/user_change_email";
        }
        catch(Exception e){
			e.printStackTrace();
			session.setAttribute("message",new Message(e.getMessage(),"alert-danger"));
            return "normal/user_change_email";
        }
    }
}