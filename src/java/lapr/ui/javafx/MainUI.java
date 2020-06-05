package lapr.ui.javafx;

import autorizacao.model.SessaoUtilizador;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import lapr.controller.AppPOE;
import lapr.utils.Role;

import java.io.IOException;

public class MainUI {
    public MainUI() {

    }

    public static void alert(String message) {
        alert(Alert.AlertType.ERROR, message);
    }

    public static void alert(Alert.AlertType type, String message) {
        Alert a = new Alert(type, message);
        a.showAndWait();
    }

    Stage stage = new Stage();
    Parent root;
    Scene scene;

    private void openWindow(String fxml_s, String title) {
        try {
            root = FXMLLoader.load(getClass().getResource(fxml_s));
        } catch (IOException e) {
            alert("Error on fxml file: " + fxml_s + "\nError:\n" + e.getMessage());
            System.out.println(e.getMessage());
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

        do {
            // Select role
            // TODO: complete me.
            final SessaoUtilizador su = AppPOE.getInstance().getSessaoAtual();
            if (su.isLoggedInComPapel(Role.ADMINISTRATOR)) {
                openWindow("/fxml/mainMenuAdmin.fxml", "Main Menu Administrator");
            } else if (su.isLoggedInComPapel(Role.COLLABORATOR)) {
                openWindow("/fxml/mainMenuCollaborator.fxml", "Main Menu Collaborator");
            } else {
                alert("Unknown Role!");
                System.exit(1);
            }
        } while (enterUC());
    }

    private boolean enterUC() {
        if(FXBridge.data instanceof String) {
            String data = (String) FXBridge.data;
            FXBridge.data = null;
            switch (data) {
                case "UC8" :
                    openWindow("/fxml/registOrg.fxml", "UC8 Add Organization");
                    return true;
                case "UC3":
                    openWindow("/fxml/AddFreelancer.fxml", "Add New Freelancer");
                    return true;
                case "UC2":
                    openWindow("/fxml/createTask.fxml", "UC2 Create Task");
                    return true;
                case "UC1":
                    openWindow("/fxml/newTransaction.fxml", "UC1 Add New Transaction");
                    return true;
                default:
                    alert("Unknow UC: " + data);
                    return false;
            }
        } else {
            FXBridge.data = null;
            return false;
        }
    }
}
