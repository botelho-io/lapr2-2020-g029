package lapr.lists;

import lapr.model.Freelancer;
import lapr.utils.Expertise;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class RegistFreelancerTest {

    RegistFreelancer rf;
    Freelancer f1; // in regist
    Freelancer f2; // in regist
    Freelancer f3; // == f1
    Freelancer f4; // unique

    @BeforeEach
    void setUp() {
        rf = new RegistFreelancer();
        rf.registerFreelancer(f1 = rf.newFreelancer("Fre Fre", Expertise.SENIOR, "mail@mail.com", "123", "123", "add", "Pt"));
        rf.registerFreelancer(f2 = rf.newFreelancer("Fre Fre", Expertise.SENIOR, "mail2@mail.com", "1234", "1234", "add", "Pt"));
        f3 = rf.newFreelancer("Fre Fre", "  SeNiOr ", "  mail@mail.com   ", "123 ", " 123", "add", "pt");
        f4 = new Freelancer("FF4", "Fre Fre", Expertise.SENIOR, "mail5@mail.com", "12345", "12345", "add", "Pt");
    }

    @Test
    void newFreelancer() {
        Freelancer expected = f1;
        Freelancer result = f3;
        assertEquals(expected, result);
    }

    @Test
    void testExistsId() {
        assertTrue(rf.existsId("FF1"));
        assertTrue(rf.existsId("FF2"));
        assertFalse(rf.existsId("FF3"));
    }

    @Test
    void testValidate() {
        assertFalse(rf.validate(f1));
        assertFalse(rf.validate(f3));
        assertTrue(rf.validate(f4));
    }

    @Test
    void addFreelancer() {
        assertTrue(rf.validate(f4));
        assertTrue(rf.registerFreelancer(f4));
        assertFalse(rf.validate(f4));
    }

    @Test
    void testNewFreelancer() {
        Freelancer expected = new Freelancer("FF1", "Fre Fre", Expertise.SENIOR, "mail@mail.com", "123", "123", "add", "Pt");
        Freelancer actual = new RegistFreelancer().newFreelancer("Fre Fre", " SENIOR ", "mail@mail.com", "123", "123", "add", "Pt");
        assertEquals(expected, actual);
        try {
            new RegistFreelancer().newFreelancer("Fre Fre", " dskljsdljsdlkfsdjlks ", "mail@mail.com", "123", "123", "add", "Pt");
            assertTrue(false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    void testNewFreelancer1() {
        Freelancer expected = new Freelancer("FF1", "Fre Fre", Expertise.SENIOR, "mail@mail.com", "123", "123", "add", "Pt");
        Freelancer actual = new RegistFreelancer().newFreelancer("Fre Fre", Expertise.SENIOR, "mail@mail.com", "123", "123", "add", "Pt");
        assertEquals(expected, actual);
    }

    @Test
    void testAddFreelancer() {
        assertFalse(rf.registerFreelancer(f1));
        assertFalse(rf.registerFreelancer(f3));
        assertTrue(rf.registerFreelancer(f4));
    }

    @Test
    void getFreelancers() {
        Collection<Freelancer> fres = rf.getFreelancers();
        assertTrue(fres.contains(f1));
        assertTrue(fres.contains(f2));
        assertTrue(fres.contains(f3));
        assertFalse(fres.contains(f4));
        assertEquals(fres.size(), 2);
    }

    @Test
    void getEqualFreelancer() {
        assertSame(rf.getSameFreelancer(f3), f1);
        assertNull(rf.getSameFreelancer(f4));
    }
}