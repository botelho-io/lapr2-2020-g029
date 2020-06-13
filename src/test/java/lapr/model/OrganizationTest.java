package lapr.model;

import lapr.controller.AppPOE;
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
        AppPOE.getInstance().getApp().getAuthFacade().registerUser(col);
        assertFalse(Organization.validatesCollaborator(col));
    }

    @Test
    void validatesManager() {
        Manager manager = Organization.newManager("Man Joe", "man@dei.pt", "password");
        assertTrue(Organization.validatesManager(manager));
        AppPOE.getInstance().getApp().getAuthFacade().registerUser(manager);
        assertFalse(Organization.validatesManager(manager));
    }

    @Test
    void validateOrganization() {
        Collaborator col = Organization.newCollaborator("Colab Joe", "colab@dei.pt", "password");
        Manager manager = Organization.newManager("Man Joe", "man@dei.pt", "password");
        //AppPOE.getInstance().getApp().getAuthFacade().registUser(manager);
        //AppPOE.getInstance().getApp().getAuthFacade().registUser(col);
        assertTrue(new Organization("123", "Name", manager, col).validateOrganization());
    }

    @Test
    void validatesPaymentScheduler() {
        assertTrue(Organization.validatesPaymentScheduler(1, LocalTime.now()));
        assertFalse(Organization.validatesPaymentScheduler(31, LocalTime.now()));
    }

    @Test
    void testValidatesPaymentScheduler() {
        Collaborator col = Organization.newCollaborator("Colab Joe", "colab@dei.pt", "password");
        Manager manager = Organization.newManager("Man Joe", "man@dei.pt", "password");
        Organization org = AppPOE.getInstance().getApp().getRegistOrganization().newOrganization("28934734", "LEL", manager, col);
        assertFalse(org.setPaymentScheduler(0, LocalTime.now()));
        assertTrue(org.setPaymentScheduler(1, LocalTime.now()));
        assertTrue(org.setPaymentScheduler(2, LocalTime.now()));
    }

    @Test
    void testEquals() {
        Collaborator col = Organization.newCollaborator("Colab Joe", "colab@dei.pt", "password");
        Manager manager = Organization.newManager("Man Joe", "man@dei.pt", "password");
        Organization org = AppPOE.getInstance().getApp().getRegistOrganization().newOrganization("28934734", "LEL", manager, col);
        Collaborator col2 = Organization.newCollaborator("Colab Joe", "colab2@dei.pt", "password");
        Manager manager2 = Organization.newManager("Man Joe", "man2@dei.pt", "password");
        Organization org2 = AppPOE.getInstance().getApp().getRegistOrganization().newOrganization("28934734", "LEL", manager2, col2);
        assertEquals(org, org2);
    }
}