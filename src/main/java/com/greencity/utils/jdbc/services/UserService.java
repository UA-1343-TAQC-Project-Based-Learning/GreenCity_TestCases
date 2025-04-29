package com.greencity.utils.jdbc.services;

import com.greencity.utils.jdbc.dao.UserDAO;
import com.greencity.utils.jdbc.entity.UserEntity;

import java.util.List;

public class UserService {
    private final UserDAO userDAO;


    public UserService(String url, String login, String password) {
        this.userDAO = new UserDAO(url, login, password);
    }


    public List<UserEntity> getAllUsers() {
        return userDAO.selectAll();
    }
    public UserEntity getUserByEmail(String email) {
        return userDAO.selectByEmail(email);
    }
}
