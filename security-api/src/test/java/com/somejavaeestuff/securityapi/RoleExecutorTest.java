package com.somejavaeestuff.securityapi;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ejb.EJB;
import javax.ejb.EJBAccessException;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class RoleExecutorTest {

    private EJBContainer ejbContainer;

    @EJB(name = "AdminExecutor")
    private RoleExecutor.AdminExecutor adminExecutor;

    @EJB(name = "OperatorExecutor")
    private RoleExecutor.OperatorExecutor operatorExecutor;

    @EJB
    private UserBean userBean;

    @BeforeEach
    public void setUp() throws NamingException {
        Properties p = new Properties();
        p.put("userDb", "new://Resource?type=DataSource");
        p.put("userDb.JdbcDriver", "org.hsqldb.jdbcDriver");
        p.put("userDb.JdbcUrl", "jdbc:hsqldb:mem:userdatabase");
        this.ejbContainer = EJBContainer.createEJBContainer(p);
        this.ejbContainer.getContext().bind("inject", this);
    }

    @AfterEach
    public void tearDown() {
        this.ejbContainer.close();
    }

    @Test
    public void asAdmin() throws Exception {
        adminExecutor.run(() -> {
            userBean.add(new User(1L, "john doe", "j.doe@test.test"));
            userBean.add(new User(2L, "peter doe", "p.doe@test.test"));
            userBean.add(new User(3L, "matt doe", "m.doe@test.test"));
            userBean.add(new User(4L, "olaf doe", "o.doe@test.test"));

            var users = userBean.get();

            users.forEach(userBean::remove);
            assertEquals(0, userBean.get().size());
        });
    }

    @Test
    public void asOperator() throws Exception {
        operatorExecutor.run(() -> {
            userBean.add(new User(1L, "user1", "user1@user.com"));
            userBean.add(new User(2L, "user2", "user2@user.com"));
            userBean.add(new User(3L, "user3", "user3@user.com"));
            userBean.add(new User(4L, "user4", "user4@user.com"));

            var list = userBean.get();

            list.forEach(user -> {
                try {
                    userBean.remove(user);
                    fail("Operator was able to remove user " + user.getName());
                } catch (EJBAccessException e) {
                }
            });

            assertEquals(4, userBean.get().size());
        });
    }

    @Test
    public void asAnonymous() {
        try {
            userBean.add(new User(1L, "elder", "elder@eldermoraes.com"));
            fail("Anonymous user should not add users");
        } catch (EJBAccessException e) {

        }

        try {
            userBean.remove(new User(1L, "elder", "elder@eldermoraes.com"));
            fail("Anonymous user should not remove users");
        } catch (EJBAccessException e) {

        }

        try {
            userBean.get();
        } catch (EJBAccessException e) {
            fail("Everyone can list users");
        }
    }
}
