package model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

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
        u.setId(1);
        u.setEmail("test@freeuni.edu.ge");
        u.setPasswordHash("secure123");
        u.setFullName("Giorgi Giorgadze");
        u.setRole("PLAYER");

        Set<ConstraintViolation<user>> violations = validator.validate(u);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testInvalidEmail() {
        user u = new user();
        u.setEmail("invalid-email-format");
        u.setPasswordHash("secure123");
        u.setFullName("Giorgi");
        u.setRole("COACH");

        Set<ConstraintViolation<user>> violations = validator.validate(u);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void testShortPassword() {
        user u = new user();
        u.setEmail("test@freeuni.edu.ge");
        u.setPasswordHash("123");
        u.setFullName("Giorgi");
        u.setRole("ADMIN");

        Set<ConstraintViolation<user>> violations = validator.validate(u);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void testBlankFields() {
        user u = new user();
        u.setEmail("");
        u.setPasswordHash("   ");
        u.setFullName("");

        Set<ConstraintViolation<user>> violations = validator.validate(u);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void testParameterizedConstructorAndGetters() {
        user u = new user(10, "coach@freeuni.edu.ge", "hashedPassword", "COACH", "Luka Luka");

        assertEquals(10, u.getId());
        assertEquals("coach@freeuni.edu.ge", u.getEmail());
        assertEquals("hashedPassword", u.getPasswordHash());
        assertEquals("COACH", u.getRole());
        assertEquals("Luka Luka", u.getFullName());
    }

    @Test
    public void testSetters() {
        user u = new user();
        u.setId(99);
        u.setEmail("admin@freeuni.edu.ge");
        u.setPasswordHash("adminPass123");
        u.setRole("ADMIN");
        u.setFullName("Anano Ananidze");

        assertEquals(99, u.getId());
        assertEquals("admin@freeuni.edu.ge", u.getEmail());
        assertEquals("adminPass123", u.getPasswordHash());
        assertEquals("ADMIN", u.getRole());
        assertEquals("Anano Ananidze", u.getFullName());
    }
}