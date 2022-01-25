package com.ticket.system.user;

import java.util.List;

public interface UserService {
    
    public void registerUser(User user);

    public List<User> findAll();

    public User findById(int id);

}
