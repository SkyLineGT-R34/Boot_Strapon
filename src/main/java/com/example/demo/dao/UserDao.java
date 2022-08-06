package com.example.demo.dao;

import com.example.demo.model.Users;

import java.util.List;

public interface UserDao {
    void create(Users user);
    Users read(long id);
    void update(long id, Users user);
    void delete(long id);
    List<Users> readAll();

    Users findUserByEmail(String email);
}
