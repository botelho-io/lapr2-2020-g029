package lapr.model;

import java.io.Serializable;
import java.util.Objects;

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
     * Returns true if the payment has been made, false otherwise.
     * @return rue if the payment has been made, false otherwise.
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PaymentDetails)) return false;
        PaymentDetails that = (PaymentDetails) o;
        return isPayed() == that.isPayed();
    }

    @Override
    public int hashCode() {
        return Objects.hash(isPayed());
    }
}
