package lapr.ui.javafx;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lapr.controller.CreateTransactionController;
import lapr.model.Freelancer;
import lapr.model.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Spinner<Integer> spDays;
    @FXML
    public TextArea taDesc;

    private Map<String, Task> name_task;
    private Map<String, Freelancer> name_freelancer;
    private CreateTransactionController controller;
    @FXML
    public void initialize() {
        controller = new CreateTransactionController();
        refreshTasks();
        refreshFreelancer();
        final SpinnerValueFactory<Integer> vFac = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,Integer.MAX_VALUE, 0);
        spDays.setValueFactory(vFac);
        taDesc.setText("");
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
        List<Freelancer> lf = controller.getFreelancers();
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
        openWindow(new Stage(),"/fxml/AddFreelancer.fxml", "Add New Freelancer");
        refreshFreelancer();
    }

    //Stage stgUC2;
    @FXML
    public void startUC2(ActionEvent actionEvent) {
        openWindow(new Stage(),"/fxml/createTask.fxml", "UC2 Create Task");
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
            boolean nt = controller.newTransaction(fre, tsk, dpDate.getValue(), spDays.getValue(), taDesc.getText());
            if (!nt) throw new IllegalStateException("An error occurred while creating the transaction.\nPlease, try again.");
            boolean ad = controller.addTransaction();
            if (!ad) throw new IllegalStateException("An error occurred while adding the transaction to the register.\nPlease, try again.");
        } catch (IllegalArgumentException | IllegalStateException ex) {
            MainUI.alert(ex.getMessage());
            initialize();
            return;
        }
        // All went ok.
        final String msg = String.format("The freelancer (%s) %s will be payed %f€.\nYou may quit or keep adding transactions.", fre.getId(), fre.getName(), controller.getAmount());
        MainUI.alert(Alert.AlertType.INFORMATION, msg);
        initialize();
    }

    private void openWindow(Stage stage, String fxml_s, String title) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource(fxml_s));
        } catch (IOException e) {
            MainUI.alert("Error on fxml file: " + fxml_s + "\nError:\n" + e.getMessage());
            System.out.println(e.getMessage());
            System.exit(1);
            e.printStackTrace();
            return;
        }
        Scene scene = new Scene(root);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
