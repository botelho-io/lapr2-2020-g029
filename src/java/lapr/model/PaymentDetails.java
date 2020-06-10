package lapr.model;

import java.io.Serializable;

/**
 * Represents the details of a payment made on a transaction.
 */
public class PaymentDetails implements Serializable {
    /**
     * Represents whether the payment has been made (true) or not (false).
     */
    private boolean isPayed;

    /**
     * Constructor.
     * @param isPayed True if the payment has been made, false otherwise.
     */
    public PaymentDetails(boolean isPayed) {
        this.isPayed = isPayed;
    }

    /**
     * Creates an unpaid detail.
     */
    public PaymentDetails() {
        this(false);
    }

    /**
     * @return True if the payment has been made, false otherwise.
     */
    public boolean isPayed() {
        return isPayed;
    }

    /**
     * @param payed True if the payment has been made, false otherwise.
     */
    public void setPayed(boolean payed) {
        isPayed = payed;
    }
}
