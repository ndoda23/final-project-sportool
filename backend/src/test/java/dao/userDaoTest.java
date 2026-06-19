package dao;

import model.user;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class userDaoTest {

    private userDao dao;

    @BeforeEach
    public void setUp() {
        dao = mock(userDao.class);
    }

    @Test
    public void testRegisterUserInDatabase() {
        user u = new user();
        u.setEmail("test@freeuni.edu.ge");
        u.setPasswordHash("hashed_password_123");
        u.setFullName("Test User");

        when(dao.registerUser(any(user.class))).thenReturn(true);

        boolean isRegistered = dao.registerUser(u);
        assertTrue(isRegistered);
    }
}