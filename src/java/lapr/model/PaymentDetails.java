package lapr.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents the details of a payment made on a transaction.
 * @author André Botelho and Ricardo Moreira.
 */
public class PaymentDetails implements Serializable {
    /**
     * Represents whether the payment has been made (true) or not (false).
     */
    private boolean isPaid;

    /**
     * Constructor.
     * @param isPaid True if the payment has been made, false otherwise.
     */
    public PaymentDetails(boolean isPaid) {
        this.isPaid = isPaid;
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
    public boolean isPaid() {
        return isPaid;
    }

    /**
     * @param paid True if the payment has been made, false otherwise.
     */
    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    /**
     * Compare two payment details.
     * @param o Ideally a payment details to compare.
     * @return Will return true if and only if the object provided is a payment details that is paid.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PaymentDetails)) return false;
        PaymentDetails that = (PaymentDetails) o;
        return isPaid() == that.isPaid();
    }

    @Override
    public int hashCode() {
        return Objects.hash(isPaid());
    }
}
