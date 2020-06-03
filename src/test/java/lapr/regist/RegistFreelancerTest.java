package lapr.regist;

import lapr.model.Freelancer;
import lapr.utils.Expertise;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegistFreelancerTest {

    RegistFreelancer rf;
    Freelancer f1;
    Freelancer f2;
    Freelancer f3;

    @BeforeEach
    void setUp() {
        rf = new RegistFreelancer();
        rf.addFreelancer(f1 = rf.newFreelancer("Fre Fre", Expertise.SENIOR, "mail@mail.com", "123", "123", "add", "Pt"));
        rf.addFreelancer(f2 = rf.newFreelancer("Fre    Fre", Expertise.SENIOR, "mail2@mail.com", "1234", "1234", "add", "Pt"));
        f3 = rf.newFreelancer("Fre Fre", "  SeNiOr ", "  mail@mail.com   ", "123 ", " 123", "add", "pt");
    }

    @Test
    void newFreelancer() {
        Freelancer expected = f1;
        Freelancer result = f3;
        assertEquals(expected, result);
    }

    @Test
    void existsId() {
        assertTrue(rf.existsId("FF1"));
        assertTrue(rf.existsId("FF2"));
        assertFalse(rf.existsId("FF3"));
    }

    @Test
    void validate() {
        assertFalse(rf.validate(f1));
        assertTrue(rf.validate(f3));
    }

    @Test
    void addFreelancer() {
        assertTrue(rf.validate(f3));
        assertTrue(rf.addFreelancer(f3));
        assertFalse(rf.validate(f3));
    }
}