package lapr.ui.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainMenuManager {

    @FXML
    public Button btnUC5dps;

    @FXML
    public void defineTime (ActionEvent actionEvent) {
        FXBridge.data = (Object) "UC5";
        quit();
    }

    private void quit() {
        ((Stage)btnUC5dps.getScene().getWindow()).close();
    }
}

