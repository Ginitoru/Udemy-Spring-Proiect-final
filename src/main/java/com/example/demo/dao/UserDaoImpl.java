package com.example.demo.dao;

import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao{

    private final EntityManager entityManager;

    @Autowired
    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void createUser(User user) {
        entityManager.persist(user);

    }

    @Override
    public User findUser(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> findAllUsesrs() {

        String jpql = "SELECT u FROM User u ";

        return entityManager.createQuery(jpql, User.class)
                .getResultList();
    }

    @Override
    public int updateUser(User user) {

        String jpql = "UPDATE User u SET u.firstName =: username, u.lastName = :lastName, u.emailAdress =: emailAdress WHERE u.id =: id ";

        return entityManager.createQuery(jpql)
                    .setParameter("username", user.getFirstName())
                    .setParameter("lastName", user.getLastName())
                    .setParameter("emailAdress", user.getEmailAdress())
                    .setParameter("id", user.getId())
                    .executeUpdate();

    }

    @Override
    public int deleteUser(int id) {

        String jpql = "DELETE FROM User u WHERE u.id =: id";

        return entityManager.createQuery(jpql)
                    .executeUpdate();
    }



    @Override
    public Optional<User> findByUsername(String userName) {

        String jpql = "SELECT u FROM User u WHERE u.userName =: userName";

        return entityManager.createQuery(jpql, User.class)
                    .setParameter("userName", userName)
                    .getResultStream().findFirst();
    }

    @Override
    public Optional<User> findByEmail(String emailAdress) {

        String jpql ="SELECT u FROM User u WHERE u.emailAdress =:emailAdress";

        return entityManager.createQuery(jpql, User.class)
                        .setParameter("emailAdress", emailAdress)
                        .getResultStream().findFirst();

    }
}
