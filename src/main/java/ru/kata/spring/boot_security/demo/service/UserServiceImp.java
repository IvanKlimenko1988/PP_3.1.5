package ru.kata.spring.boot_security.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.dao.UserDaoImp;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

@Service
public class UserServiceImp implements UserService {
    private final UserDao userDaoImp;

    @Autowired
    public UserServiceImp(UserDaoImp userDaoImp) {
        this.userDaoImp = userDaoImp;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userDaoImp.getAllUsers();
    }

    @Override
    @Transactional
    public void add(User user) {
        userDaoImp.add(user);
    }

    @Override
    @Transactional
    public void delete(int id) {
        userDaoImp.delete(id);
    }

    @Override
    @Transactional
    public void edit(User user) {
        userDaoImp.edit(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User getById(int id) {
        return userDaoImp.getById(id);
    }

}