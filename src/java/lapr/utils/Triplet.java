package lapr.utils;

/**
 * Represents a collection of 3 elements.
 * @param <T1> Type of the first element.
 * @param <T2> Type of the second element.
 * @param <T3> Type of the third element.
 */
public class Triplet<T1, T2, T3> {
    /**
     * First element.
     */
    T1 first;
    /**
     * Second element.
     */
    T2 second;
    /**
     * Third element.
     */
    T3 third;

    /**
     * Constructor.
     * @param first First element.
     * @param second Second element.
     * @param third Third element.
     */
    public Triplet(T1 first, T2 second, T3 third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public T1 getFirst() {
        return first;
    }

    public void setFirst(T1 first) {
        this.first = first;
    }

    public T2 getSecond() {
        return second;
    }

    public void setSecond(T2 second) {
        this.second = second;
    }

    public T3 getThird() {
        return third;
    }

    public void setThird(T3 third) {
        this.third = third;
    }
}
