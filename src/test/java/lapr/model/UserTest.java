package lapr.model;

import authorization.model.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    User usr;

    @BeforeEach
    void setUp() {
        usr = new User("name", "email", "password", Role.ADMINISTRATOR);
    }

    @Test
    void getName() {
        assertEquals(usr.getName(), "name");
    }

    @Test
    void setName() {
        assertEquals(usr.getName(), "name");
        usr.setName("name1");
        assertEquals(usr.getName(), "name1");
    }

    @Test
    void getEmail() {
        assertEquals(usr.getEmail(), "email");
    }

    @Test
    void setEmail() {
        assertEquals(usr.getEmail(), "email");
        usr.setEmail("email1");
        assertEquals(usr.getEmail(), "email1");
    }

    @Test
    void hasEmail() {
        assertTrue(usr.hasEmail("email"));
        assertFalse(usr.hasEmail("email1"));
    }

    @Test
    void hasPassword() {
        assertTrue(usr.hasPassword("password"));
        assertFalse(usr.hasPassword("paSsword"));
    }

    @Test
    void hasRole() {
        assertTrue(usr.hasRole(Role.ADMINISTRATOR));
        assertFalse(usr.hasRole(Role.COLLABORATOR));
    }

    @Test
    void getRole() {
        assertEquals(Role.ADMINISTRATOR, usr.getRole());
    }
}