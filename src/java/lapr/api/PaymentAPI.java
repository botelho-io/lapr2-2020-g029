package lapr.api;

import lapr.model.Payment;

/**
 * Represents an API used to process bank payments.
 */
public interface PaymentAPI {
    Payment payTo(Double amount, String freelancerIBAN);
}
