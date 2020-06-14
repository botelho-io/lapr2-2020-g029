package lapr.api.defaults;

import lapr.api.PaymentAPI;
import lapr.utils.Constants;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents an API adapter used to process bank payments.
 */
public class PaymentAPIAdapter implements PaymentAPI {
    FileWriter fstream;
    BufferedWriter out;

    public PaymentAPIAdapter() {
        try {
            fstream = new FileWriter(Constants.paymentsFile, true);
            out = new BufferedWriter(fstream);
            if(new File(Constants.paymentsFile).length() == 0)
                out.write("Freelancer ID\tFreelancer IBAN\tTask ID\tTask Description\tEuro\tNative Currency\n");
        } catch (IOException e) {
            // TODO: handle exception.
            System.out.println(e.getMessage());
        }
    }

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
        try {
            out.write(String.format("%s\t%s\t%s\t%s\t%.2f\t%.2f\n", freelancerId, freelancerIBAN, taskId, taskDescription, eur, nativeCurrency));
            return true;
        } catch (IOException e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void close() throws IOException {
        out.flush();
        out.close();
        fstream.close();
    }
}
