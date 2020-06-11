package lapr.ui.javafx;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lapr.controller.AdministratorStatisticsController;
import lapr.ui.javafx.util.FXBridge;
import lapr.ui.javafx.util.HelperUI;
import lapr.utils.GenEur;
import lapr.utils.HourConverter;

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

    AdministratorStatisticsController ctr;
    @FXML
    public void initialize() {
        if(!(FXBridge.param instanceof AdministratorStatisticsController)) {
            HelperUI.alert("Error on FXBridge");
            quit(null);
        } else {
            ctr = (AdministratorStatisticsController) FXBridge.param;
            PspBS.setValueFactory(new GenEur(1, 4, 25));
            SpinnerValueFactory<Double> sp = new SpinnerValueFactory.DoubleSpinnerValueFactory(5, Double.MAX_VALUE, 60, 5);
            sp.setConverter(new HourConverter());
            DspBS.setValueFactory(sp);
            SpinnerValueFactory<Double> sp2 = new SpinnerValueFactory.DoubleSpinnerValueFactory(Double.MIN_VALUE, Double.MAX_VALUE, 3*60, 5);
            sp2.setConverter(new HourConverter());
            sp2.valueProperty().addListener(new ChangeListener<Double>() {
                @Override public void changed(ObservableValue<? extends Double> observableValue, Double aDouble, Double t1) {
                    updateProb();
                }
            });
            DspHOURS.setValueFactory(sp2);
            updateProb();
            PlblM.setText(String.format("%.2f €", ctr.getMeanPayments()));
            DlblM.setText(String.format("%.2f hrs", ctr.getMeanDelays()));
            PlblSD.setText(String.format("%.2f", ctr.getStandardDeviationPayments()));
            DlblSD.setText(String.format("%.2f", ctr.getStandardDeviationDelays()));

            applyBucketSizeDelays(null);
            applyBucketSizePayments(null);
        }
    }

    private boolean updateProb() {
        final double prob = ctr.getProbabilityMeanDelayLessThan(DspHOURS.getValue()/60);
        DlblPROB.setText(String.format("%.2f%% (%.6f)", prob*100, prob));
        return true;
    }

    @FXML
    public void quit(ActionEvent actionEvent) {
        ((Stage)DspBS.getScene().getWindow()).close();
    }

    @FXML
    public void applyBucketSizePayments(ActionEvent actionEvent) {
        applyBucketSize(PspBS.getValue(), ctr::getHistogramDataPayments, PbcH, "Payment Amount (€)");
    }

    @FXML
    public void applyBucketSizeDelays(ActionEvent actionEvent) {
        applyBucketSize(DspBS.getValue()/60, ctr::getHistogramDataDelays, DbcH, "Delay (hrs)");
    }

    private void applyBucketSize(final double width, Function<Double, Map<Integer, Integer>> mapGetter, BarChart<String, Integer> bc, String name) {
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
