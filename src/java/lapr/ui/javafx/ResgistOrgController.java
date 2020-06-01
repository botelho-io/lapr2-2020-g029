package lapr.ui.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lapr.controller.AddOrganizationController;


public class ResgistOrgController {

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
    public void preformResgistOrg (ActionEvent actionEvent) {
        final String name = fieldOrganization.getText();
        final String nameC = fieldNameCollaborator.getText();
        final String mailC = fieldEmailCollaborator.getText();
        final String nameM = fieldNameManager.getText();
        final String mailM = fieldEmailManager.getText();
        final boolean succsess = AddOrganizationController.getInstace.newOrganization(name, nameC,mailC,nameM,mailM);
        if(succsess) {
            // Close window, main controller knows if there was a successful registration.
            ((Stage) fieldOrganization.getScene().getWindow()).close();
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Organization with wrong data!!!");
            a.show();
        }
    }
}

