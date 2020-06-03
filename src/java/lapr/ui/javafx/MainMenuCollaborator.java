package lapr.ui.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainMenuCollaborator {
    @FXML
    public Button btnUC3NFre;

    @FXML
    public void addFre(ActionEvent actionEvent) {
        FXBridge.data = (Object) "UC3";
        quit();
    }

    private void quit() {
        ((Stage)btnUC3NFre.getScene().getWindow()).close();
    }
}
