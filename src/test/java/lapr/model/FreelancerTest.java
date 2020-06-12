package lapr.model;

import lapr.controller.AppPOE;
import lapr.regist.RegistFreelancer;
import lapr.utils.Expertise;
import lapr.utils.Role;
import lapr.utils.TestConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FreelancerTest {

        Freelancer free;

        @BeforeEach
        void setUp() {
        free = new Freelancer("FJ1", "Free Joe1", Expertise.SENIOR, "fre1@dei.pt", "28739247891", "8937431", "Address1", "Germany");
        }

        @Test
        void getId() {
        assertEquals(free.getId(), "FJ1");
        }

        @Test
        void getName() {
        assertEquals(free.getName(), "Free Joe1");
        }

        @Test
        void getEmail() {
        assertEquals(free.getEmail(), "fre1@dei.pt");
        }

        @Test
        void getNIF() {
        assertEquals(free.getNIF(), "28739247891");
        }

        @Test
        void getIBAN() {
        assertEquals(free.getIBAN(), "8937431");
        }

        @Test
        void getAddress(){
        assertEquals(free.getAddress(), "Address1");
        }

        @Test
        void getCountry(){
        assertEquals(free.getCountry(), "Germany");
    }

        @Test
        void getLevelOfExpertise() {
            assertEquals(free.getLevelOfExpertise(), Expertise.SENIOR);
        }



    }