package lapr.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentDetailsTest {

    @Test
    void isPayed() {
        PaymentDetails pd = new PaymentDetails();
        assertFalse(pd.isPayed());
    }

    @Test
    void setPayed() {
        PaymentDetails pd = new PaymentDetails();
        pd.setPayed(true);
        assertTrue(pd.isPayed());
    }
}