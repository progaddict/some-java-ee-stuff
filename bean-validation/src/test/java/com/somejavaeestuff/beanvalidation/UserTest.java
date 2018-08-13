package com.somejavaeestuff.beanvalidation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void testValidUser() {
        var user = new User(
                "john doe",
                "j.doe@test.test",
                asList(1, 2, 3, 4, 5)
        );
        var cv = validator.validate(user);
        assertTrue(cv.isEmpty());
    }

    @Test
    public void testInvalidName() {
        var user = new User(
                "",
                "j.doe@test.test",
                asList(1, 2, 3, 4, 5)
        );
        var cv = validator.validate(user);
        assertEquals(1, cv.size());
    }

    @Test
    public void testInvalidEmail() {
        var user = new User(
                "john doe",
                "j<>doe@test",
                asList(1, 2, 3, 4, 5)
        );
        var cv = validator.validate(user);
        assertEquals(1, cv.size());
    }

    @Test
    public void testInvalidId() {
        var user = new User(
                "john doe",
                "j.doe@test.test",
                asList(1, 2, -3, 4, 5)
        );
        var cv = validator.validate(user);
        assertEquals(1, cv.size());
    }
}
