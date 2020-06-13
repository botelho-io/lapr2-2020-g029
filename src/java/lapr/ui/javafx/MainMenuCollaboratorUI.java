package lapr.ui.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import lapr.ui.javafx.util.FXBridge;

public class MainMenuCollaboratorUI {
    @FXML
    public Button btnUC3NFre;

    @FXML
    public Button btnUC2CTask;

    @FXML
    public Button btnUC7ViIn;


    @FXML
    public void addTransaction(ActionEvent actionEvent) {
        FXBridge.scene = FXBridge.STATE.UC1;
        quit();
    }

    @FXML
    public void addFre(ActionEvent actionEvent) {
        FXBridge.scene = FXBridge.STATE.UC3;
        quit();
    }

    @FXML
    public void createTask (ActionEvent actionEvent) {
        FXBridge.scene = FXBridge.STATE.UC2;
        quit();
    }

    @FXML
    public void visInf (ActionEvent actionEvent) {
        FXBridge.scene = FXBridge.STATE.UC7;
        quit();
    }

    private void quit() {
        ((Stage)btnUC3NFre.getScene().getWindow()).close();
    }

    public void enterUC4(ActionEvent actionEvent) {
        FXBridge.scene = FXBridge.STATE.UC4;
        quit();
    }
}
