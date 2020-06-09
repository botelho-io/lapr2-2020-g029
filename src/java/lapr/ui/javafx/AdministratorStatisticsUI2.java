package lapr.ui.javafx;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import lapr.controller.AdministratorStatisticsController;
import lapr.ui.javafx.util.FXBridge;
import lapr.ui.javafx.util.HelperUI;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;

public class AdministratorStatisticsUI2 {
    // Payments
    @FXML
    public Label PlblM; // Mean
    @FXML
    public Label PlblSD; // Standard deviation
    @FXML
    public BarChart<String, Integer> PbcH;
    @FXML
    public Spinner<Double> PspBS;

    // Delays
    @FXML
    public Label DlblM; // Mean
    @FXML
    public Label DlblSD; // Standard deviation
    @FXML
    public Label DlblPROB; // Probability
    @FXML
    public BarChart<String, Integer> DbcH;
    @FXML
    public Spinner<Double> DspBS;
    @FXML
    public Spinner<Double> DspHOURS;

    private class SPGenEUR extends SpinnerValueFactory<Double> {
        final double STEP;
        final double MIN;
        int t;
        public SPGenEUR(double min, int step_begin, double step) {
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

    private class HrConverter extends StringConverter<Double> {
        @Override public String toString(Double aDouble) {
            return String.format("%.2f hrs", aDouble);
        }
        @Override public Double fromString(String s) {
            return null;
        }
    }

    private class SPGenHour extends SpinnerValueFactory<Double> {
        final double STEP;
        final double MIN;
        int t;
        public SPGenHour(double min, int step_begin, double step) {
            STEP = step;
            t = step_begin;
            MIN = min;
            this.setValue(Math.max(MIN, t*STEP));
            this.setConverter(new HrConverter());
        }
        @Override public void decrement(int i) {
            increment(-i);
        }
        @Override public void increment(int i) {
            t = Math.max(0, t+i);
            this.setValue(Math.max(MIN, t*STEP));
            updateProb();
        }
    }

    AdministratorStatisticsController ctr;
    @FXML
    public void initialize() {
        if(!(FXBridge.param instanceof AdministratorStatisticsController)) {
            HelperUI.alert("Error on FXBridge");
            quit(null);
        } else {
            ctr = (AdministratorStatisticsController) FXBridge.param;
            PspBS.setValueFactory(new SPGenEUR(1, 4, 25));
            SpinnerValueFactory<Double> sp = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.5, Double.MAX_VALUE, 1, 0.5);
            sp.setConverter(new HrConverter());
            DspBS.setValueFactory(sp);
            DspHOURS.setValueFactory(new SPGenHour(0.05, 12, 0.25));
            updateProb();
            PlblM.setText(String.format("%.2f €", ctr.getMeanPayments()));
            DlblM.setText(String.format("%.2f hrs", ctr.getMeanDelays()));
            PlblSD.setText(String.format("%.2f", ctr.getStandardDeviationPayments()));
            DlblSD.setText(String.format("%.2f", ctr.getStandardDeviationDelays()));

            applyBucketSizeDelays(null);
            applyBucketSizePayments(null);
        }
    }

    private void updateProb() {
        final double prob = ctr.getProbabilityMeanDelayLessThan(DspHOURS.getValue());
        DlblPROB.setText(String.format("%.2f%% (%.6f)", prob*100, prob));
    }

    @FXML
    public void quit(ActionEvent actionEvent) {
        ((Stage)DspBS.getScene().getWindow()).close();
    }

    @FXML
    public void applyBucketSizePayments(ActionEvent actionEvent) {
        applyBucketSize(PspBS, ctr::getHistogramDataPayments, PbcH, "Payment Amount (€)");
    }

    @FXML
    public void applyBucketSizeDelays(ActionEvent actionEvent) {
        applyBucketSize(DspBS, ctr::getHistogramDataDelays, DbcH, "Delay (hrs)");
    }

    private void applyBucketSize(final Spinner<Double> sp, Function<Double, Map<Integer, Integer>> mapGetter, BarChart<String, Integer> bc, String name) {
        final double width = sp.getValue();
        final Map<Integer, Integer> map = mapGetter.apply(width);
        final Integer min = Collections.min(map.keySet());
        final Integer max = Collections.max(map.keySet());

        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        series.setName(name);
        final ObservableList<XYChart.Data<String, Integer>> data = series.getData();
        for(Integer i = min; i <= max; i++) {
            Integer value = map.get(i);
            if(value == null) value = 0;
            final String range = String.format("[%.2f, %.2f[", i*width, (i+1)*width);
            data.add(new XYChart.Data<>(range, value));
        }
        bc.getData().clear();
        bc.getData().add(series);
    }
}
