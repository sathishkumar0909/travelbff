package com.sk.travel.service;

import com.sk.travel.model.User;
import com.sk.travel.service.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface UserService {

    User createUser(User user);

    void deleteUser(long id);

//    User getUser(String phonenumber);

    List<User> getAllUsers();
    User getUserByEmail(String email);
}
