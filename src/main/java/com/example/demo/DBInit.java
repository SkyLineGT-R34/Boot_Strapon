package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.example.demo.model.Roles;
import com.example.demo.model.Users;
import com.example.demo.service.RolesService;
import com.example.demo.service.UserService;

import javax.annotation.PostConstruct;

@Component
public class DBInit {
    private final UserService userService;
    private final RolesService rolesService;

    @Autowired
    public DBInit(UserService userService, RolesService rolesService) {
        this.userService = userService;
        this.rolesService = rolesService;
    }

    @PostConstruct
    private void postConstruct() {
        rolesService.save(new Roles("ROLE_ADMIN"));
        rolesService.save(new Roles("ROLE_USER"));
        String[] rolesA = {"ROLE_ADMIN", "ROLE_USER"};
        userService.create(new Users("admin", "admin", "admin@mail.ru", "admin", 1999), rolesA, "admin");
        String[] rolesU = {"ROLE_USER"};
        userService.create(new Users("user", "user", "user@mail.ru", "user", 2000), rolesU, "user");
    }
}