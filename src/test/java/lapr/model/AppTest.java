package lapr.model;

import lapr.api.EmailAPI;
import lapr.api.MonetaryConversionAPI;
import lapr.api.PaymentAPI;
import lapr.api.PswGeneratorAPI;
import lapr.controller.AppPOE;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {


    App app = AppPOE.getInstance().getApp();
    EmailAPI eapi = app.getEmailAPI();
    PswGeneratorAPI psapi = app.getPswGeneratorAPI();
    MonetaryConversionAPI mcapi = app.getMonetaryConversionAPI();
    PaymentAPI pmapi = app.getPaymentAPI();
    

    @Test
    void getPswGeneratorAPI() {assertEquals(app.getPswGeneratorAPI(), psapi); }

    @Test
    void getPaymentAPI() { assertEquals(app.getPaymentAPI(), pmapi);}

    @Test
    void getMonetaryConversionAPI() { assertEquals(app.getMonetaryConversionAPI(), mcapi); }

    @Test
    void getEmailAPI() { assertEquals(app.getEmailAPI(),eapi); }


}