package lapr.ui.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lapr.controller.AppPOE;
import lapr.ui.javafx.util.HelperUI;
import lapr.ui.javafx.util.MainUI;

public class LoginUI {
    @FXML
    public TextField fieldEmail;
    @FXML
    public PasswordField fieldPassword;
    @FXML
    public void preformLogin(ActionEvent actionEvent) {
        final String mail = fieldEmail.getText();
        final String password = fieldPassword.getText();
        try {
            if(mail == null || mail.isEmpty()) throw new IllegalStateException("Email field cannot be empty!");
            if(password == null || password.isEmpty()) throw new IllegalStateException("Password field cannot be empty!");
        } catch (IllegalStateException e) {
            HelperUI.alert(e.getMessage());
            return;
        }
        final boolean succsess = AppPOE.getInstance().doLogin(mail, password);
        if(succsess) {
            // Close window, main controller knows if there was a successful login.
            ((Stage) fieldEmail.getScene().getWindow()).close();
        } else {
            HelperUI.alert("Wrong e-mail or password");
        }
    }
}
