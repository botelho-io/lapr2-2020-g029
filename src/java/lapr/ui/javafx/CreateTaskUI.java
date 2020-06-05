package lapr.ui.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lapr.controller.CreateTaskController;
import lapr.controller.CreateTransactionController;

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
            if(succsess) {
                ((Stage) fieldID.getScene().getWindow()).close();
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Task with wrong data!!!");
                a.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void btnCancelAction(ActionEvent actionEvent) {
    }

    private String validateString(String data, String dataName) {
        if(data == null || (data = data.trim()).isEmpty()) {
            throw new IllegalArgumentException(dataName + " cannot be empty!");
        } else return data;
    }
}
