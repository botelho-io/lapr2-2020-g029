package lapr.ui.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lapr.controller.CreateTaskController;
import lapr.ui.javafx.util.HelperUI;
import lapr.ui.javafx.util.MainUI;

public class CreateTaskUI {

    private static CreateTaskController controller;

    @FXML
    public TextField fieldID;
    @FXML
    public TextField fieldDescription;
    @FXML
    public TextField fieldDuration;
    @FXML
    public TextField fieldCost;
    @FXML
    public TextField fieldCategory;
    @FXML
    public Button btnCancel;
    @FXML
    public Button btnCreate;

    @FXML
    public void initialize() {
        controller = new CreateTaskController();
    }

    @FXML
    public void btnCreateAction(ActionEvent actionEvent) {
        try {
            String ID = validateString(fieldID.getText(), "ID");
            String Description = validateString(fieldDescription.getText(), "Description");
            String Duration = validateString(fieldDuration.getText(), "Duration");
            String Cost = validateString(fieldCost.getText(), "Cost");
            String Category = validateString(fieldCategory.getText(), "Category");
            final boolean succsess = controller.newTask( ID, Description, Integer.parseInt(Duration), Double.parseDouble(Cost), Category);
            if(!succsess) throw new IllegalStateException("Task with wrong data!!!");
            final boolean succsess2 = controller.registTask();
            if(!succsess2)  throw new IllegalStateException("Task with wrong data!!!");
            quit();
        } catch (Exception e) {
            HelperUI.alert("An error occurred:\n" + e.getMessage());
        }
    }

    @FXML
    public void btnCancelAction(ActionEvent actionEvent) {
        quit();
    }

    private void quit() {
        ((Stage) fieldID.getScene().getWindow()).close();
    }

    private String validateString(String data, String dataName) {
        if(data == null || (data = data.trim()).isEmpty()) {
            throw new IllegalArgumentException(dataName + " cannot be empty!");
        } else return data;
    }
}
