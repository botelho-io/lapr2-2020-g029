package lapr.ui.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainMenuAdminUI {

    @FXML
    public Button btnUC8NOrg;

    @FXML
    public void addOrg(ActionEvent actionEvent) {
        FXBridge.data = (Object) "UC8";
        quit();
    }

    private void quit() {
        ((Stage)btnUC8NOrg.getScene().getWindow()).close();
    }
}
