package com.somejavaeestuff.securityapi;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.Collections;
import java.util.List;

import static javax.persistence.PersistenceContextType.EXTENDED;

@Stateful
public class UserBean {

    @PersistenceContext(unitName = "MyPU", type = EXTENDED)
    private EntityManager entityManager;

    @RolesAllowed({Roles.ADMIN, Roles.OPERATOR})
    public void add(User user) {
        entityManager.persist(user);
    }

    @RolesAllowed({Roles.ADMIN})
    public void remove(User user) {
        entityManager.remove(user);
    }

    @RolesAllowed({Roles.ADMIN})
    public void update(User user) {
        entityManager.merge(user);
    }

    @PermitAll
    public List<User> get() {
        var q = entityManager.createQuery("SELECT u FROM User u");
        return Collections.unmodifiableList(q.getResultList());
    }
}
