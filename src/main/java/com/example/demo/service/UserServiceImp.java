package com.example.demo.service;

import com.example.demo.dao.RolesDao;
import com.example.demo.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.model.Users;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {
    private final UserDao userDao;
    private final RolesDao rolesDao;
    private final RolesService rolesService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImp(UserDao userDao, RolesDao rolesDao, RolesService rolesService, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.rolesDao = rolesDao;
        this.rolesService = rolesService;
        this.passwordEncoder = passwordEncoder;
    }


    //CRUD
    @Transactional
    @Override
    public void create(Users user, String[] roles, String password) {
        user.setRoles(Arrays.stream(roles).map(rolesService::findByRole).collect(Collectors.toList()));
        user.setPassword(passwordEncoder.encode(password));
        userDao.create(user);
    }

    @Override
    public Users read(long id) {
        return userDao.read(id);
    }

    @Transactional
    @Override
    public void update(long id, Users updatedUser, String[] roles, String password) {
        Users user = read(id);
        if (password != null) {
            updatedUser.setPassword(passwordEncoder.encode(password));
        } else {
            updatedUser.setPassword(user.getPassword());
        }
        if (roles != null) {
            updatedUser.setRoles(Arrays.stream(roles).map(rolesService::findByRole).collect(Collectors.toList()));
        } else {
            updatedUser.setRoles(user.getRoles());
        }
        userDao.update(id, updatedUser);
    }

    @Transactional
    @Override
    public void delete(long id) {
        userDao.delete(id);
    }

    @Override
    public List<Users> readAll() {
        return userDao.readAll();
    }

    @Override
    public Users findUserByEmail(String email) {
        return userDao.findUserByEmail(email);

    }
}