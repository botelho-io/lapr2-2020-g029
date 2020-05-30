package lapr.ui.javafx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import lapr.controller.AppPOE;
import lapr.utils.Constants;

import java.io.IOException;

public class MainJFXController {
    public MainJFXController() {

    }

    Stage stage = new Stage();
    Parent root;
    Scene scene;

    private void openWindow(String fxml_s, String title) {
        try {
            root = FXMLLoader.load(getClass().getResource(fxml_s));
        } catch (IOException e) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Unknowu fxml file: " + fxml_s);
            a.showAndWait();
            System.exit(1);
            e.printStackTrace();
        }
        scene = new Scene(root);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void start() throws Exception {

        // Login
        openWindow("/fxml/login.fxml", "Login");
        // Was login successful?
        if(AppPOE.getInstance().getSessaoAtual() == null || (!AppPOE.getInstance().getSessaoAtual().isLoggedIn())) {
            System.exit(0); // Not successful - Exit
        }

        // Select role
        // TODO: complete me.
        if(AppPOE.getInstance().getSessaoAtual().isLoggedInComPapel(Constants.ROLE_ADMINISTRATOR)) {
            openWindow("/fxml/mainMenuAdmin.fxml", "Main Menu Administrator");
        } else {
            System.out.println("Unknown Role!");
            System.exit(1);
        }
        enterUC();
    }

    private void enterUC() {
        if(FXBridge.data instanceof String) {
            String data = (String) FXBridge.data;
            switch (data) {
                case "UC8":
                    // TODO: open UC8
                    break;
                default:
                    Alert a = new Alert(Alert.AlertType.ERROR, "Unknowu UC: " + data);
                    a.showAndWait();
                    System.exit(1);
                    break;
            }
        } else {
            System.exit(0);
        }
    }
}
