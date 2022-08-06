package com.example.demo.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Users;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class UserDaoImp implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Users user){
        entityManager.persist(user);
    }
    @Override
    public Users read(long id){
        return entityManager.find(Users.class, id);
    }
    @Override
    public void update(long id, Users updatedUser) {
        entityManager.merge(updatedUser);
    }
    @Override
    public void delete(long id) {
        entityManager.remove(read(id));
    }
    @Override
    public List<Users> readAll() {
        return entityManager.createQuery("SELECT u FROM Users u", Users.class).getResultList();
    }
    @Override
    public Users findUserByEmail(String email) {
        return entityManager
                .createQuery("SELECT u FROM Users u WHERE u.email = : email", Users.class)
                .setParameter("email", email)
                .getSingleResult();
    }
}
