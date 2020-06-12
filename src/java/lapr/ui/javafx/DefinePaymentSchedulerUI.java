package lapr.ui.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lapr.controller.DefinePaymentSchedulerController;
import lapr.ui.javafx.util.HelperUI;

import java.time.LocalTime;

public class DefinePaymentSchedulerUI {
    @FXML
    public Button btnSet;
    @FXML
    public Button btnCancel;

    @FXML
    public Spinner<Integer> spDay;
    @FXML
    public Spinner<Integer> spHour;
    @FXML
    public Spinner<Integer> spMin;


    DefinePaymentSchedulerController ctr;
    @FXML
    public void initialize() {
        ctr = new DefinePaymentSchedulerController();
        SpinnerValueFactory<Integer> vf = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 28, 8, 1);
        spDay.setValueFactory(vf);
        vf = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 12, 1);
        spHour.setValueFactory(vf);
        vf = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 55, 30, 5);
        spMin.setValueFactory(vf);
    }


    @FXML
    public void btnSetAction (ActionEvent actionEvent) {
        try {
            final Integer dayOfMonth = spDay.getValue();
            final LocalTime timeOfDay = LocalTime.of(spHour.getValue(), spMin.getValue());
            ctr.newPaymentScheduler( dayOfMonth, timeOfDay);
            btnCancelAction();
        } catch (Exception e) {
            HelperUI.alert(e.getMessage());
        }
    }

    @FXML
    public void btnCancelAction() {
        ((Stage)spMin.getScene().getWindow()).close();
    }
}
