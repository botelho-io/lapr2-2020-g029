package lapr.ui.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import lapr.controller.DeserializeController;
import lapr.ui.javafx.util.HelperUI;

import java.io.IOException;

public class DeserializeUI {

    @FXML
    public Button btnLoad;

    DeserializeController ctr;
    @FXML
    public void initialize() {
        ctr = new DeserializeController();
        if(!ctr.hasDataFile()) {
            // No data file to deserialize.
            quit();
        }
    }

    @FXML
    public void load(ActionEvent actionEvent) {
        try {
            ctr.deserialize();
        } catch (IOException | ClassNotFoundException  e) {
            HelperUI.alert(e.getMessage());
            System.exit(-1);
        }
    }

    @FXML
    public void noLoad(ActionEvent actionEvent) {
        quit();
    }

    @FXML
    public void cancel(ActionEvent actionEvent) {
        System.exit(0);
    }

    private void quit() {
        ((Stage)btnLoad.getScene().getWindow()).close();
    }
}
