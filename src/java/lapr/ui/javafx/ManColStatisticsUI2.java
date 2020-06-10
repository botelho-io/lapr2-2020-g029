package lapr.ui.javafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import lapr.controller.AdministratorStatisticsController;
import lapr.controller.ManagerCollaboratorStatisticsController;
import lapr.model.Freelancer;
import lapr.ui.javafx.util.FXBridge;
import lapr.ui.javafx.util.HelperUI;

import java.io.FileReader;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;

public class ManColStatisticsUI2 {
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
    public BarChart<String, Integer> DbcH;
    @FXML
    public Spinner<Double> DspBS;

    // UC7
    @FXML
    public TableColumn<TableData, String> tcId;
    @FXML
    public TableColumn<TableData, String> tcName;
    @FXML
    public TableColumn<TableData, String> tcCountry;
    @FXML
    public TableColumn<TableData, String> tcEmail;
    @FXML
    public TableColumn<TableData, String> tcExpertise;
    @FXML
    public TableColumn<TableData, Double> tcPayment;
    @FXML
    public TableView<TableData> table;

    public class TableData {
        final String id;
        final String name;
        final String country;
        final String email;
        final String expertise;
        final Double payment;

        public TableData(String id, String name, String country, String email, String expertise, Double payment) {
            this.id = id;
            this.name = name;
            this.country = country;
            this.email = email;
            this.expertise = expertise;
            this.payment = payment;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getCountry() {
            return country;
        }

        public String getEmail() {
            return email;
        }

        public String getExpertise() {
            return expertise;
        }

        public Double getPayment() {
            return payment;
        }
    }

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


    ManagerCollaboratorStatisticsController ctr;
    @FXML
    public void initialize() {
        if(!(FXBridge.param instanceof ManagerCollaboratorStatisticsController)) {
            HelperUI.alert("Error on FXBridge");
            quit(null);
        } else {
            ctr = (ManagerCollaboratorStatisticsController) FXBridge.param;
            PspBS.setValueFactory(new SPGenEUR(1, 4, 25));
            SpinnerValueFactory<Double> sp = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.5, Double.MAX_VALUE, 1, 0.5);
            sp.setConverter(new HrConverter());
            DspBS.setValueFactory(sp);
            PlblM.setText(String.format("%.2f €", ctr.getMeanPayments()));
            DlblM.setText(String.format("%.2f hrs", ctr.getMeanDelays()));
            PlblSD.setText(String.format("%.2f", ctr.getStandardDeviationPayments()));
            DlblSD.setText(String.format("%.2f", ctr.getStandardDeviationDelays()));
            applyBucketSizeDelays(null);
            applyBucketSizePayments(null);

            tcCountry.setCellValueFactory( new PropertyValueFactory<>("country"));
            tcEmail.setCellValueFactory( new PropertyValueFactory<>("email"));
            tcExpertise.setCellValueFactory( new PropertyValueFactory<>("expertise"));
            tcId.setCellValueFactory( new PropertyValueFactory<>("id"));
            tcName.setCellValueFactory( new PropertyValueFactory<>("name"));
            tcPayment.setCellValueFactory( new PropertyValueFactory<>("payment"));

            final ObservableList<TableData> data = FXCollections.observableArrayList();

            for(final Freelancer f : ctr.getFreelancers()) {
                final TableData td = new TableData(
                        f.getId(),
                        f.getName(),
                        f.getCountry(),
                        f.getEmail(),
                        f.getLevelOfExpertise().name(),
                        10.4 // TODO change 10 for actual value
                );
                data.add(td);
            }
            table.setItems(data);
        }
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
