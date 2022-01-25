package com.ticket.system.roles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{
    
    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }
    
    public List<Role> findAll(){
        return roleRepository.findAll();
    }
}
