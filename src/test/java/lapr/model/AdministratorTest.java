package lapr.model;

import lapr.utils.TestConstants;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdministratorTest {

    Administrator admin = TestConstants.getTestAdmin();

    @Test
    void Administrator() {
        Administrator expected = admin;
        Administrator result = new Administrator("Admin Joe", "admin@dei.pt", "password");
        assertEquals(expected, result);
    }
}