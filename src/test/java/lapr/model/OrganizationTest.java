package lapr.model;

import lapr.controller.AppPOE;
import lapr.utils.TestConstants;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class OrganizationTest {

    @BeforeEach
    void setUp() throws IOException {
        AppPOE.restartInstance();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void newCollaborator() {
        Collaborator expected = new Collaborator("Colab Joe", "colab@dei.pt", "password");
        Collaborator observed = Organization.newCollaborator("Colab Joe", "colab@dei.pt", "password");
        assertEquals(expected, observed);
    }

    @Test
    void newManager() {
        Manager expected = new Manager("Man Joe", "man@dei.pt", "password");
        Manager observed = Organization.newManager("Man Joe", "man@dei.pt", "password");
        assertEquals(expected, observed);
    }

    @Test
    void validatesCollaborator() {
        Collaborator col = Organization.newCollaborator("Colab Joe", "colab@dei.pt", "password");
        assertTrue(Organization.validatesCollaborator(col));
        AppPOE.getInstance().getApp().getAuthFacade().registUser(col);
        assertFalse(Organization.validatesCollaborator(col));
    }

    @Test
    void validatesManager() {
        Manager manager = Organization.newManager("Man Joe", "man@dei.pt", "password");
        assertTrue(Organization.validatesManager(manager));
        AppPOE.getInstance().getApp().getAuthFacade().registUser(manager);
        assertFalse(Organization.validatesManager(manager));
    }

    @Test
    void validateOrganization() {
        Collaborator col = Organization.newCollaborator("Colab Joe", "colab@dei.pt", "password");
        Manager manager = Organization.newManager("Man Joe", "man@dei.pt", "password");
        //AppPOE.getInstance().getApp().getAuthFacade().registUser(manager);
        //AppPOE.getInstance().getApp().getAuthFacade().registUser(col);
        assertTrue(new Organization("Name", manager, col).validateOrganization());
    }

    @Test
    void validatesPaymentScheduler() {
        assertTrue(Organization.validatesPaymentScheduler(1, LocalTime.now()));
        assertFalse(Organization.validatesPaymentScheduler(31, LocalTime.now()));
    }
}