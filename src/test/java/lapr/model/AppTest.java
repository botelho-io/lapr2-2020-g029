package lapr.model;

import lapr.api.*;
import lapr.api.sout.*;
import lapr.controller.AppPOE;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    EmailAPI eapi;
    PswGeneratorAPI psapi;
    MonetaryConversionAPI mcapi;
    PaymentAPI pmapi;

    @BeforeEach
    void setUp() throws IOException {
        AppPOE.restartInstance();
        eapi = new EmailAPIAdapter();
        psapi = new PswGeneratorAPIAdapter();
        mcapi = new MonetaryConversionAPIAdapter();
        pmapi = new PaymentAPIAdapter();
        AppPOE.getInstance().getApp().setAPIs(eapi, mcapi, pmapi, psapi);
    }

    @Test
    void getPswGeneratorAPI() {
        assertSame(AppPOE.getInstance().getApp().getPswGeneratorAPI(), psapi);
    }

    @Test
    void getPaymentAPI() {
        assertSame(AppPOE.getInstance().getApp().getPaymentAPI(), pmapi);
    }

    @Test
    void getMonetaryConversionAPI() {
        assertSame(AppPOE.getInstance().getApp().getMonetaryConversionAPI(), mcapi);
    }

    @Test
    void getEmailAPI() {
        assertSame(AppPOE.getInstance().getApp().getEmailAPI(), eapi);
    }


}