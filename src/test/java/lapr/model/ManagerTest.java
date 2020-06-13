package lapr.model;

import authorization.model.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {

    Manager man;

    @BeforeEach
    void setUp() {
        man = new Manager("Man Joe", "man@dei.pt", "password");
    }

    @Test
    void getName() {
        assertEquals(man.getName(), "Man Joe");
    }

    @Test
    void setName() {
        assertEquals(man.getName(), "Man Joe");
        man.setName("Man Joe1");
        assertEquals(man.getName(), "Man Joe1");
    }

    @Test
    void getEmail() {
        assertEquals(man.getEmail(), "man@dei.pt");
    }

    @Test
    void setEmail() {
        assertEquals(man.getEmail(), "man@dei.pt");
        man.setEmail("man1@dei.pt");
        assertEquals(man.getEmail(), "man1@dei.pt");
    }

    @Test
    void hasEmail() {
        assertTrue(man.hasEmail("man@dei.pt"));
        assertFalse(man.hasEmail("man@dei1.pt"));
    }

    @Test
    void hasPassword() {
        assertTrue(man.hasPassword("password"));
        assertFalse(man.hasPassword("paSsword"));
    }

    @Test
    void hasRole() {
        assertTrue(man.hasRole(Role.MANAGER));
        assertFalse(man.hasRole(Role.ADMINISTRATOR));
    }

}