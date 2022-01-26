package com.ticket.system.user;

import java.util.List;

import javax.validation.Valid;

import com.ticket.system.roles.Role;
import com.ticket.system.roles.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService){
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping("/list")
    public String userListView(Model model){
        
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "auth/list-view";

    }

    @GetMapping("/register")
    public String registerView(Model model){
        
        User user = new User();
        model.addAttribute("form", user);
        List<Role> roles = roleService.findAll();
        model.addAttribute("roles", roles);
        return "auth/register-view";

    }
    
    @PostMapping("/save")
    public String saveUserView(@Valid @ModelAttribute("form") User user, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            
            List<Role> roles = roleService.findAll();
            model.addAttribute("roles", roles);
            return "auth/register-view";

        }
        userService.registerUser(user);
        return "redirect:/user/list";
    }

    @GetMapping("/update")
    public String getUserUpdateView(@ModelAttribute("userId") int id, Model model){
        
        User user = userService.findById(id);
        model.addAttribute("form", user);
        List<Role> roles = roleService.findAll();
        model.addAttribute("roles", roles);
        
        return "auth/register-view";

    }

    @GetMapping("/delete")
    public String userDeleteView(@ModelAttribute("userId") int id){
        
        User user = userService.findById(id);
        userService.delete(user);
        return "redirect:/list";

    }

}
