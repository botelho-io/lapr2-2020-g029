package main.ui.javafx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.controller.AppPOE;

import java.io.IOException;

public class MainJFXController {
    public MainJFXController() {

    }

    public void runApp() throws IOException {
        Stage stage = new Stage();

        // Login
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.showAndWait();

        // Was login successful?
        if(AppPOE.getInstance().getSessaoAtual() == null || (!AppPOE.getInstance().getSessaoAtual().isLoggedIn())) {
            System.exit(0); // Not successful - Exit
        }

        // Select role
        // TODO: complete me.
        System.out.println("SUCCESS");
    }
}
