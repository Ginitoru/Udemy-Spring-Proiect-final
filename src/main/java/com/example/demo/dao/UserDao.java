package com.example.demo.dao;

import com.example.demo.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    void createUser(User user);

     User  findUser(int id);

     List<User> findAllUsesrs();

     int updateUser(User user);

     int deleteUser(int id);


     Optional<User> findByUsername(String username);

     Optional<User> findByEmail(String emialAdress);
}
