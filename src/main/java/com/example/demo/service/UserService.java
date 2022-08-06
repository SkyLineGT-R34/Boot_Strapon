package com.example.demo.service;

import com.example.demo.model.Users;

import java.util.List;

public interface UserService {
    void create(Users user, String[] roles, String password);
    Users read(long  id);
    void update(long  id, Users user, String[] roles, String password);
    void delete(long  id);
    List<Users> readAll();
    Users findUserByEmail(String email);
}
