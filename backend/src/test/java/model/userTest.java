package model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class userTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidUser() {
        user u = new user();
        u.setEmail("test@freeuni.edu.ge");
        u.setPasswordHash("secure123");
        u.setFullName("Giorgi");

        Set<ConstraintViolation<user>> violations = validator.validate(u);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testInvalidEmail() {
        user u = new user();
        u.setEmail("invalid-email");
        u.setPasswordHash("secure123");
        u.setFullName("Giorgi");

        Set<ConstraintViolation<user>> violations = validator.validate(u);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void testShortPassword() {
        user u = new user();
        u.setEmail("test@freeuni.edu.ge");
        u.setPasswordHash("123");
        u.setFullName("Giorgi");

        Set<ConstraintViolation<user>> violations = validator.validate(u);
        assertFalse(violations.isEmpty());
    }
}