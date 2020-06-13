package lapr.model;

import authorization.model.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CollaboratorTest {


    Collaborator coll;

    @BeforeEach
    void setUp() {
        coll = new Collaborator("Colab Joe", "colab@dei.pt", "password");
    }

    @Test
    void getName() {
        assertEquals(coll.getName(), "Colab Joe");
    }

    @Test
    void setName() {
        assertEquals(coll.getName(), "Colab Joe");
        coll.setName("Colab Joe1");
        assertEquals(coll.getName(), "Colab Joe1");
    }

    @Test
    void getEmail() {
        assertEquals(coll.getEmail(), "colab@dei.pt");
    }

    @Test
    void setEmail() {
        assertEquals(coll.getEmail(), "colab@dei.pt");
        coll.setEmail("colab1@dei.pt");
        assertEquals(coll.getEmail(), "colab1@dei.pt");
    }

    @Test
    void hasEmail() {
        assertTrue(coll.hasEmail("colab@dei.pt"));
        assertFalse(coll.hasEmail("colab1@dei.pt"));
    }

    @Test
    void hasPassword() {
        assertTrue(coll.hasPassword("password"));
        assertFalse(coll.hasPassword("paSsword"));
    }

    @Test
    void hasRole() {
        assertTrue(coll.hasRole(Role.COLLABORATOR));
        assertFalse(coll.hasRole(Role.ADMINISTRATOR));
    }

}