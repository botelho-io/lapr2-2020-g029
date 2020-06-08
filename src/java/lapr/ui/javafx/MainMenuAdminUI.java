package lapr.ui.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import lapr.ui.javafx.util.FXBridge;
import lapr.ui.javafx.util.MainUI;

public class MainMenuAdminUI {

    @FXML
    public Button btnUC8NOrg;

    @FXML
    public void enterUC08(ActionEvent actionEvent) {
        FXBridge.scene = FXBridge.UC.UC8;
        quit();
    }

    private void quit() {
        ((Stage)btnUC8NOrg.getScene().getWindow()).close();
    }

    public void enterUC09(ActionEvent actionEvent) {
        FXBridge.scene = FXBridge.UC.UC9;
        quit();
    }
}
