package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.NoSuchElementException;


@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    @Transactional
    public void createUser(User user) {

        if(userDao.findByUsername(user.getUserName()).isEmpty() ||
            userDao.findByEmail(user.getEmailAdress()).isEmpty()){

            userDao.createUser(user);
        }else{
            throw new NoSuchElementException();
        }



    }

    @Override
    @Transactional
    public User findUser(int id) {
        return userDao.findUser(id);
    }

    @Override
    @Transactional
    public List<User> findAllUsesrs() {
        return userDao.findAllUsesrs();
    }

    @Override
    @Transactional
    public int updateUser(User user) {

        return userDao.updateUser(user);
    }


    @Override
    @Transactional
    public int deleteUser(int id) {
        return userDao.deleteUser(id);
    }

    @Override
    @Transactional
    public User findbyUserName(String userName) {
       return userDao.findByUsername(userName)
               .orElseThrow(NoSuchElementException::new);
    }

    @Override
    @Transactional
    public User findByEmail(String emailAdress) {

        return userDao.findByEmail(emailAdress)
                    .orElseThrow(NoSuchElementException::new);

    }
}
