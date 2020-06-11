package lapr.utils;

import javafx.scene.control.SpinnerValueFactory;
import javafx.util.StringConverter;

public class GenEur extends SpinnerValueFactory<Double> {
    final double STEP;
    final double MIN;
    int t;
    public GenEur(double min, int step_begin, double step) {
        STEP = step;
        t = step_begin;
        MIN = min;
        this.setValue(Math.max(MIN, t*STEP));
        this.setConverter(new StringConverter<Double>() {
            @Override public String toString(Double aDouble) {
                return String.format("%.2f €", aDouble);
            }
            @Override public Double fromString(String s) { return 0.5; }
        });
    }
    @Override public void decrement(int i) {
        increment(-i);
    }
    @Override public void increment(int i) {
        t = Math.max(0, t+i);
        this.setValue(Math.max(MIN, t*STEP));
    }
}