package com.example.demo.service;

import com.example.demo.dao.RolesDao;
import com.example.demo.model.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RolesServiceImp implements RolesService {
    private final RolesDao rolesDao;
    public RolesServiceImp(RolesDao rolesDao) {
        this.rolesDao = rolesDao;
    }

    @Override
    public Roles findByRole(String role) {
        return rolesDao.findByRole(role);
    }

    @Transactional
    @Override
    public void save(Roles role) {
        rolesDao.save(role);
    }

    @Override
    public List<Roles> getAllRoles() {
        return rolesDao.getAllRoles();
    }
}
