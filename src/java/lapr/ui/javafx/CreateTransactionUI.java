package lapr.ui.javafx;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lapr.controller.CreateTransactionController;
import lapr.model.Freelancer;
import lapr.model.Task;
import lapr.ui.javafx.util.FXBridge;
import lapr.ui.javafx.util.HelperUI;

import java.time.LocalDate;
import java.util.*;
import java.util.zip.DeflaterOutputStream;

public class CreateTransactionUI {
    @FXML
    public Button btnUC3;
    @FXML
    public ChoiceBox<Freelancer> cbFreelancer;
    @FXML
    public Button btnUC2;
    @FXML
    public ChoiceBox<Task> cbTask;
    @FXML
    public DatePicker dpDate;
    @FXML
    public Spinner<Double> spHours;
    @FXML
    public TextArea taDesc;
    @FXML
    public Label lblValue;
    @FXML
    public TextField tfId;

    private CreateTransactionController controller;
    @FXML
    public void initialize() {
        controller = new CreateTransactionController();
        refreshTasks();
        refreshFreelancer();
        final SpinnerValueFactory<Double> vFac = new SpinnerValueFactory.DoubleSpinnerValueFactory(Double.MIN_VALUE,Double.MAX_VALUE, 0);
        spHours.setValueFactory(vFac);
        taDesc.setText("");
        tfId.setText("");
        cbFreelancer.getSelectionModel().selectedItemProperty().addListener(ncl());
        cbTask.getSelectionModel().selectedItemProperty().addListener(ncl());
        taDesc.textProperty().addListener(ncl());
        spHours.valueProperty().addListener(ncl());
        dpDate.valueProperty().addListener(ncl());
        updateValue(cbFreelancer.getValue(), cbTask.getValue(), dpDate.getValue(), spHours.getValue(), taDesc.getText());
    }

    private <T> ChangeListener<T> ncl() {
        return new ChangeListener<T>() {
            @Override public void changed(ObservableValue<? extends T> obs, T oldval, T newval) {
                if(newval instanceof Freelancer) {
                    updateValue((Freelancer) newval, cbTask.getValue(), dpDate.getValue(), spHours.getValue(), taDesc.getText());
                } else if (newval instanceof Task) {
                    updateValue(cbFreelancer.getValue(), (Task) newval, dpDate.getValue(), spHours.getValue(), taDesc.getText());
                } else if (newval instanceof LocalDate) {
                    updateValue(cbFreelancer.getValue(), cbTask.getValue(), (LocalDate) newval, spHours.getValue(), taDesc.getText());
                } else if (newval instanceof  Double) {
                    updateValue(cbFreelancer.getValue(), cbTask.getValue(), dpDate.getValue(), (Double) newval, taDesc.getText());
                } else if (newval instanceof  String) {
                    updateValue(cbFreelancer.getValue(), cbTask.getValue(), dpDate.getValue(), spHours.getValue(), (String) newval);
                } else {
                    throw new IllegalArgumentException("CreateTransactionUI - Bad listener");
                }
            }
        };
    }

    private void refreshTasks() {
        cbTask.setItems(FXCollections.observableArrayList(controller.getTasks()));
    }

    private void refreshFreelancer() {
        cbFreelancer.setItems(FXCollections.observableArrayList(controller.getFreelancers()));
    }

    @FXML
    public void startUC3(ActionEvent actionEvent) {
        FXBridge.openState(FXBridge.STATE.UC3, new Stage());
        refreshFreelancer();
    }

    @FXML
    public void startUC2(ActionEvent actionEvent) {
        FXBridge.openState(FXBridge.STATE.UC2, new Stage());
        refreshTasks();
    }

    @FXML
    public void quit(ActionEvent actionEvent) {
        ((Stage)btnUC3.getScene().getWindow()).close();
    }

    @FXML
    public void newTransaction(ActionEvent actionEvent) {
        Freelancer fre;
        Task tsk;
        try {
            newTransaction(cbFreelancer.getValue(), cbTask.getValue(), dpDate.getValue(), spHours.getValue(), taDesc.getText());
            boolean ad = controller.addTransaction();
            if (!ad) throw new IllegalStateException("An error occurred while adding the transaction to the register.\nPlease, try again.");
        } catch (IllegalArgumentException | IllegalStateException ex) {
            HelperUI.alert(ex.getMessage());
            initialize();
            return;
        }
        // All went ok.
        final String msg = String.format("The freelancer %s will be paid %f€.\nYou may quit or keep adding transactions.", cbFreelancer.getValue().toString(), controller.getAmount());
        HelperUI.alert(Alert.AlertType.INFORMATION, msg);
        initialize();
    }

    @FXML
    public void updateValue(Freelancer fre, Task tsk, LocalDate date, Double hours, String desc) {
        try {
            newTransaction(fre, tsk, date, hours, desc);
            final String msg = String.format("%.3f€", controller.getAmount());
            lblValue.setText(msg);
        } catch (IllegalArgumentException | IllegalStateException ex) {
            lblValue.setText("Incomplete Values");
        }
    }

    private void newTransaction(Freelancer fre, Task tsk, LocalDate date, Double hours, String desc) {
        if (fre == null) throw new IllegalStateException("An illegal freelancer was chosen.");
        if (tsk == null) throw new IllegalStateException("An illegal task was chosen.");
        boolean nt = controller.newTransaction(tfId.getText(), fre, tsk, date, hours, desc);
        if (!nt) throw new IllegalStateException("An error occurred while creating the transaction.");
    }
}
