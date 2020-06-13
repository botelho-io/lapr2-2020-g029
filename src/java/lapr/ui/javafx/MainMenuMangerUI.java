package lapr.ui.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import lapr.ui.javafx.util.FXBridge;


public class MainMenuMangerUI {

        @FXML
        public Button btnUC7ViIn;

        @FXML
        public void visIn (ActionEvent actionEvent) {
            FXBridge.scene = FXBridge.STATE.UC7;
            quit();
        }

        private void quit() {
            ((Stage)btnUC7ViIn.getScene().getWindow()).close();
        }


        @FXML
        public void enterUC5(ActionEvent actionEvent) {
            FXBridge.scene = FXBridge.STATE.UC5;
            quit();
        }
}
