package com.example.demo.service;

import com.example.demo.model.Roles;
import java.util.List;

public interface RolesService {
    Roles findByRole(String role);
    void save (Roles role);
    List<Roles> getAllRoles();
}