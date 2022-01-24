package com.ticket.system.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    
    @Autowired
    public UserRepository userRepository;

    @GetMapping("/register")
    public String registerView(Model model){
        
        User user = new User();
        model.addAttribute("form", user);
        return "auth/register-view";

    }

    @PostMapping("/register-process")
    public String registerProcessView(User user, Model model){
        
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return "auth/registration-success";

    }



}
