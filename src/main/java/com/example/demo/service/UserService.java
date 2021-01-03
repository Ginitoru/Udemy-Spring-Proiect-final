package com.example.demo.service;

import com.example.demo.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void createUser(User user);

    User  findUser(int id);

    List<User> findAllUsesrs();

    int updateUser(User user);

    int deleteUser(int id);

    User findbyUserName(String userName);

    User findByEmail(String emailAdress);
}
