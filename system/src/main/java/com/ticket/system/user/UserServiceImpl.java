package com.ticket.system.user;

import java.util.List;
import java.util.Optional;

import com.ticket.system.roles.Role;
import com.ticket.system.roles.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public void registerUser(User user){
        
        Role userRole = roleRepository.findByName("EMPLOYEE");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);
        user.addRole(userRole);
        
        userRepository.save(user);

    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(int id){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new RuntimeException("No user with id: " + id);
        }
        return user.get();
    }

    public void delete(User user){
        userRepository.delete(user);
    }

}
