package lapr.model;

import lapr.utils.TestConstants;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CollaboratorTest {

    Collaborator coll = TestConstants.getTestCol();

    @Test
    void Collaborator() {
        Collaborator expected = coll;
        Collaborator result = new Collaborator("Colab Joe", "colab@dei.pt", "password");
        assertEquals(expected, result);
    }
}