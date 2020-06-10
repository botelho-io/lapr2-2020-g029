package lapr.ui.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import lapr.ui.javafx.util.FXBridge;
import lapr.ui.javafx.util.MainUI;


public class MainMenuMangerUI {

        @FXML
        public Button btnUC7ViIn;

        @FXML
        public Button btnUC5dps;

        @FXML
        public void defineTime (ActionEvent actionEvent) {
            FXBridge.scene = FXBridge.UC.UC5;
            quit();
        }

        @FXML
        public void visIn (ActionEvent actionEvent) {
            FXBridge.scene = FXBridge.UC.UC7;
            quit();
        }

        private void quit() {
            ((Stage)btnUC7ViIn.getScene().getWindow()).close();
        }


}
