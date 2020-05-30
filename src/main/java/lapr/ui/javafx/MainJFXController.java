package lapr.ui.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lapr.controller.AppPOE;

import javax.print.DocFlavor;
import java.net.URL;

public class MainJFXController {
    public MainJFXController() {

    }

    public void start() throws Exception {
        Stage stage = new Stage();

        // Login
        URL t = getClass().getResource("/fxml/login.fxml");
        System.out.println(t);
        Parent root = FXMLLoader.load(t);
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
