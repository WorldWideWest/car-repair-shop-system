package com.ticket.system.user;

import java.util.List;

import com.ticket.system.roles.Role;
import com.ticket.system.roles.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    
    @Autowired
    public UserService userService;

    @Autowired
    public RoleService roleService;


    @GetMapping("/list")
    public String userListView(Model model){
        
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "auth/list-view";

    }

    @GetMapping("/update")
    public String userUpdateView(@ModelAttribute("userId") int id, Model model){
        
        User user = userService.findById(id);
        model.addAttribute("form", user);
        List<Role> roles = roleService.findAll();
        model.addAttribute("roles", roles);
        
        return "auth/register-view";

    }

    @GetMapping("/register")
    public String registerView(Model model){
        
        User user = new User();
        model.addAttribute("form", user);
        List<Role> roles = roleService.findAll();
        model.addAttribute("roles", roles);
        return "auth/register-view";

    }

    

    @PostMapping("/register-process")
    public String registerProcessView(User user, Model model){
        
        userService.registerUser(user);
        return "auth/registration-success";

    }



}
