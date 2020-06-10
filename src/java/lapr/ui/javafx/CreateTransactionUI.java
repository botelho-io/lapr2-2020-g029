package lapr.ui.javafx;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.InputMethodEvent;
import javafx.stage.Stage;
import lapr.controller.CreateTransactionController;
import lapr.model.Freelancer;
import lapr.model.Task;
import lapr.ui.javafx.util.FXBridge;
import lapr.ui.javafx.util.HelperUI;
import lapr.ui.javafx.util.MainUI;

import java.util.*;

public class CreateTransactionUI {
    @FXML
    public Button btnUC3;
    @FXML
    public ChoiceBox<String> cbFreelancer;
    @FXML
    public Button btnUC2;
    @FXML
    public ChoiceBox<String> cbTask;
    @FXML
    public DatePicker dpDate;
    @FXML
    public Spinner<Integer> spHours;
    @FXML
    public TextArea taDesc;
    @FXML
    public Label lblValue;

    private Map<String, Task> name_task;
    private Map<String, Freelancer> name_freelancer;
    private CreateTransactionController controller;
    @FXML
    public void initialize() {
        controller = new CreateTransactionController();
        refreshTasks();
        refreshFreelancer();
        final SpinnerValueFactory<Integer> vFac = new SpinnerValueFactory.IntegerSpinnerValueFactory(Integer.MIN_VALUE,Integer.MAX_VALUE, 0);
        spHours.setValueFactory(vFac);
        taDesc.setText("");
        ChangeListener<Object> cl = new ChangeListener<Object>() {
            @Override public void changed(ObservableValue<? extends Object> obs, Object o, Object o1) {
                updateValue();
            }
        };
        cbFreelancer.getSelectionModel().selectedIndexProperty().addListener(cl);
        cbTask.getSelectionModel().selectedIndexProperty().addListener(cl);
        taDesc.textProperty().addListener(cl);
        spHours.valueProperty().addListener(cl);
        updateValue();
    }

    private void refreshTasks() {
        name_task = new HashMap<>();
        List<Task> lt = controller.getTasks();
        ArrayList<String> strLt = new ArrayList<>();
        for (Task tsk : lt) {
            final String name = String.format("(%s) %s", tsk.getId(), tsk.getDescription());
            name_task.put(name, tsk);
            strLt.add(name);
        }
        cbTask.setItems(FXCollections.observableArrayList(strLt));
    }

    private void refreshFreelancer() {
        name_freelancer = new HashMap<>();
        Collection<Freelancer> lf = controller.getFreelancers();
        ArrayList<String> strLf = new ArrayList<>();
        for (Freelancer fre : lf) {
            final String name = String.format("(%s) %s", fre.getId(), fre.getName());
            name_freelancer.put(name, fre);
            strLf.add(name);
        }
        cbFreelancer.setItems(FXCollections.observableArrayList(strLf));
    }

    @FXML
    public void startUC3(ActionEvent actionEvent) {
        FXBridge.openUC(FXBridge.UC.UC3, new Stage());
        refreshFreelancer();
    }

    //Stage stgUC2;
    @FXML
    public void startUC2(ActionEvent actionEvent) {
        FXBridge.openUC(FXBridge.UC.UC2, new Stage());
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
            fre = name_freelancer.get(cbFreelancer.getValue());
            if (fre == null) throw new IllegalStateException("An illegal freelancer was chosen.\nPlease, try again.");
            tsk = name_task.get(cbTask.getValue());
            if (tsk == null) throw new IllegalStateException("An illegal task was chosen.\nPlease, try again.");
            boolean nt = controller.newTransaction(fre, tsk, dpDate.getValue(), spHours.getValue(), taDesc.getText());
            if (!nt) throw new IllegalStateException("An error occurred while creating the transaction.\nPlease, try again.");
            boolean ad = controller.addTransaction();
            if (!ad) throw new IllegalStateException("An error occurred while adding the transaction to the register.\nPlease, try again.");
        } catch (IllegalArgumentException | IllegalStateException ex) {
            HelperUI.alert(ex.getMessage());
            initialize();
            return;
        }
        // All went ok.
        final String msg = String.format("The freelancer (%s) %s will be payed %f€.\nYou may quit or keep adding transactions.", fre.getId(), fre.getName(), controller.getAmount());
        HelperUI.alert(Alert.AlertType.INFORMATION, msg);
        initialize();
    }

    @FXML
    public void updateValue() {
        Freelancer fre;
        Task tsk;
        try {
            fre = name_freelancer.get(cbFreelancer.getValue());
            if (fre == null) throw new IllegalStateException("An illegal freelancer was chosen.");
            tsk = name_task.get(cbTask.getValue());
            if (tsk == null) throw new IllegalStateException("An illegal task was chosen.");
            boolean nt = controller.newTransaction(fre, tsk, dpDate.getValue(), spHours.getValue(), taDesc.getText());
            if (!nt) throw new IllegalStateException("An error occurred while creating the transaction.");
            final String msg = String.format("%.3f€", controller.getAmount());
            lblValue.setText(msg);
        } catch (IllegalArgumentException | IllegalStateException ex) {
            lblValue.setText("Incomplete Values");
        }
    }
}
