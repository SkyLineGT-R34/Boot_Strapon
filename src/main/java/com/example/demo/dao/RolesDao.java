package com.example.demo.dao;

import com.example.demo.model.Roles;
import java.util.List;

public interface RolesDao {
    Roles findByRole (String role);
    void save (Roles role);
    List<Roles> getAllRoles();
}
