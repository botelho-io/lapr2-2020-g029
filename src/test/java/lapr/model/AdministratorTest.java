package lapr.model;

import authorization.model.UserRole;
import lapr.utils.Role;
import lapr.utils.TestConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdministratorTest {

    Administrator admin;

    @BeforeEach
    void setUp() {
        admin = new Administrator("Admin Joe", "admin@dei.pt", "password");
    }

    @Test
    void getName() {
        assertEquals(admin.getName(), "Admin Joe");
    }

    @Test
    void setName() {
        assertEquals(admin.getName(), "Admin Joe");
        admin.setName("Admin Joe1");
        assertEquals(admin.getName(), "Admin Joe1");
    }

    @Test
    void getEmail() {
        assertEquals(admin.getEmail(), "admin@dei.pt");
    }

    @Test
    void setEmail() {
        assertEquals(admin.getEmail(), "admin@dei.pt");
        admin.setEmail("admin1@dei.pt");
        assertEquals(admin.getEmail(), "admin1@dei.pt");
    }

    @Test
    void hasEmail() {
        assertTrue(admin.hasEmail("admin@dei.pt"));
        assertFalse(admin.hasEmail("admin@dei1.pt"));
    }

    @Test
    void hasPassword() {
        assertTrue(admin.hasPassword("password"));
        assertFalse(admin.hasPassword("paSsword"));
    }

    @Test
    void hasRole() {
        assertTrue(admin.hasRole(Role.ADMINISTRATOR));
        assertFalse(admin.hasRole(Role.COLLABORATOR));
    }

    }