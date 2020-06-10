package lapr.ui.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lapr.controller.CreateOrganizationController;
import lapr.ui.javafx.util.HelperUI;
import lapr.ui.javafx.util.MainUI;


public class CreateOrganizationUI {

    @FXML
    public TextField fieldOrganization;
    @FXML
    public TextField fieldNameCollaborator;
    @FXML
    public TextField fieldEmailCollaborator;
    @FXML
    public TextField fieldNameManager;
    @FXML
    public TextField fieldEmailManager;
    @FXML
    public Button btnAdd;
    @FXML
    public Button btnCancel;

    CreateOrganizationController controller;
    @FXML
    public void initialize() {
        controller = new CreateOrganizationController();
    }

    @FXML
    public void btnAddAction (ActionEvent actionEvent) {
        try {
            final String name = validateString(fieldOrganization.getText(), "name");
            final String nameC = validateString(fieldNameCollaborator.getText(), "nameC");
            final String mailC = validateString(fieldEmailCollaborator.getText() , "mailC" );
            final String nameM = validateString(fieldNameManager.getText(), "nameM" );
            final String mailM = validateString(fieldEmailManager.getText() , "mailM");
            final boolean succsess = controller.newOrganization(name, nameC,mailC,nameM,mailM);
            if(!succsess) throw new IllegalStateException("Organization with wrong data!!!");
            final boolean succsess2 = controller.registOrganizacation();
            if(!succsess2) throw new IllegalStateException("Organization with wrong data!!!");
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
        ((Stage) fieldOrganization.getScene().getWindow()).close();
    }

    private String validateString(String data, String dataName) {
        if(data == null || (data = data.trim()).isEmpty()) {
            throw new IllegalArgumentException(dataName + " cannot be empty!");
        } else return data;
    }


}

