package lapr.ui.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import lapr.controller.SerializeController;
import lapr.ui.javafx.util.FXBridge;
import lapr.ui.javafx.util.HelperUI;

import java.io.IOException;

public class SerializeUI {
    @FXML
    public Button btnSave;

    @FXML
    public void save(ActionEvent actionEvent) {
        SerializeController ctr = new SerializeController();
        try {
            ctr.serialize();
            quit(FXBridge.UC.QUIT);
        } catch (IOException ex) {
            HelperUI.alert(ex.getMessage());
            quit(FXBridge.UC.MAIN_MENU);
        }
    }

    @FXML
    public void noSave(ActionEvent actionEvent) {
        quit(FXBridge.UC.QUIT);
    }

    @FXML
    public void cancel(ActionEvent actionEvent) {
        quit(FXBridge.UC.MAIN_MENU);
    }

    private void quit(FXBridge.UC uc) {
        FXBridge.scene = uc;
        ((Stage)btnSave.getScene().getWindow()).close();
    }
}
