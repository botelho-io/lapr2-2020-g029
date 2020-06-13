package lapr.ui.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import lapr.controller.SendEmailsController;
import lapr.ui.javafx.util.FXBridge;
import lapr.ui.javafx.util.HelperUI;

public class MainMenuAdminUI {

    @FXML
    public Button btnUC8NOrg;

    @FXML
    public void enterUC08(ActionEvent actionEvent) {
        FXBridge.scene = FXBridge.STATE.UC8;
        quit();
    }

    private void quit() {
        ((Stage)btnUC8NOrg.getScene().getWindow()).close();
    }

    public void enterUC09(ActionEvent actionEvent) {
        FXBridge.scene = FXBridge.STATE.UC9;
        quit();
    }

    public void enterUC13(ActionEvent actionEvent) {
        SendEmailsController ctr = new SendEmailsController();
        ctr.getMessage();
        ctr.sendMessages();
        HelperUI.alert(Alert.AlertType.INFORMATION, "E-mails successfully sent!");
    }
}
