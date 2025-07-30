package com.lcwd.user.service.services;

import com.lcwd.user.service.entities.User;

import java.util.List;

public interface UserService {

    //createUser
    User create(User user);

    //get all User
    List<User> getAllUser();

    //get a user
    User getUser(String userId);
}
