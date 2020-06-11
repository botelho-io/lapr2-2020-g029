package lapr.model;

import lapr.controller.AppPOE;
import lapr.regist.RegistFreelancer;
import lapr.utils.Expertise;
import lapr.utils.TestConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FreelancerTest {

    Freelancer free1;
    Freelancer free2;
    Freelancer free3;

    @BeforeEach
    void setUp() throws IOException {
        AppPOE.restartInstance();

    }


    @Test
    void testEquals() {
        Freelancer[] freeLt = TestConstants.getTestFreelancers();
        Freelancer free1 = freeLt[0];
        Freelancer free2 = freeLt[1];
        Freelancer free3 = freeLt[0];
        Freelancer expected = free1;
        Freelancer result = free3;
        assertEquals(expected, result);
    }

}