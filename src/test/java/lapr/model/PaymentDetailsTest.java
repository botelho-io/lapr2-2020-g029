package lapr.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentDetailsTest {

    @Test
    void isPaid() {
        PaymentDetails pd = new PaymentDetails();
        assertFalse(pd.isPaid());
    }

    @Test
    void setPaid() {
        PaymentDetails pd = new PaymentDetails();
        pd.setPaid(true);
        assertTrue(pd.isPaid());
    }
}