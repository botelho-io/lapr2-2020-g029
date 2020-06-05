package lapr.ui.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lapr.controller.AppPOE;

public class LoginUI {
    @FXML
    public TextField fieldEmail;
    @FXML
    public PasswordField fieldPassword;
    @FXML
    public void preformLogin(ActionEvent actionEvent) {
        final String mail = fieldEmail.getText();
        final String password = fieldPassword.getText();
        final boolean succsess = AppPOE.getInstance().doLogin(mail, password);
        if(succsess) {
            // Close window, main controller knows if there was a successful login.
            ((Stage) fieldEmail.getScene().getWindow()).close();
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Wrong e-mail or password");
            a.show();
        }
    }
}
