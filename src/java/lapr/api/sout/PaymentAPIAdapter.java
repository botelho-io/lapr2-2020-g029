package lapr.api.sout;

import lapr.api.PaymentAPI;

import java.io.IOException;

public class PaymentAPIAdapter implements PaymentAPI {

    /**
     * Make a payment.
     *
     * @param freelancerId    The ID of the freelancer to pay to.
     * @param freelancerIBAN  The IBAN of the freelancer to pay.
     * @param taskId          The ID of the task this payment is for.
     * @param taskDescription The description of the task this payment is for.
     * @param eur             The amount in euros to pay to the freelancer.
     * @param nativeCurrency  The amount in the freelancer's native currency to pay.
     * @return True if the payment was successful, false otherwise.
     */
    @Override
    public boolean payTo(String freelancerId, String freelancerIBAN, String taskId, String taskDescription, Double eur, Double nativeCurrency) {
        final String msg = String.format(
                    "*******************************************************\n"+
                    "PaymentAPI - Payment to [%s (%s)]\n"+
                    "~~~~~~ BEGIN ~~~~~~\n"+
                    "Task [%s (%s)]\n"+
                    "Amount [%f€ (%f)]\n"+
                    "~~~~~~  END  ~~~~~~\n"+
                    "*******************************************************\n\n\n"
                ,freelancerIBAN, freelancerId, taskDescription, taskId, eur, nativeCurrency);
        System.out.println(msg);
        return true;
    }

    @Override
    public void close() {
    }
}
