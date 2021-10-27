package com.example.demo.service;

import com.example.demo.model.User;

public interface IUserService {

    void createUser(String id, String name);

    User getUser(String id) throws Exception;

    void updateUserName(String id, String name);
}
