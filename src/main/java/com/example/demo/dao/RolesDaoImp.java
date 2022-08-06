package com.example.demo.dao;

import com.example.demo.model.Roles;
import org.springframework.stereotype.Component;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
public class RolesDaoImp implements RolesDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Roles findByRole(String role) {
        Query query = entityManager.createQuery("SELECT r FROM Roles r WHERE r.role = : role");
        query.setParameter("role", role);
        return (Roles) query.getSingleResult();
    }

    @Override
    public void save(Roles role) {
        entityManager.persist(role);
    }

    @Override
    public List<Roles> getAllRoles() {
        return entityManager.createQuery("SELECT u FROM Roles u").getResultList();
    }
}

