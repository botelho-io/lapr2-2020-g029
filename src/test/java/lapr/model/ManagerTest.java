package lapr.model;

import lapr.utils.TestConstants;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {

    Manager man = TestConstants.getTestMan();

    @Test
    void Manager () {
        Manager expected = man;
        Manager result =new Manager("Man Joe", "man@dei.pt", "password");
        assertEquals(expected, result);
    }
}