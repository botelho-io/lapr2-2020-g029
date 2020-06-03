package lapr.ui.javafx;

import autorizacao.model.SessaoUtilizador;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import lapr.controller.AddOrganizationController;
import lapr.controller.AppPOE;
import lapr.model.Organization;
import lapr.utils.Constants;
import lapr.utils.Role;

import java.io.IOException;

public class MainJFXController {
    public MainJFXController() {

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
            alert("Unknowu fxml file: " + fxml_s);
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
            switch (data) {
                case "UC8" :
                    openWindow("/fxml/registOrg.fxml", "UC8 Add Organization");
                    FXBridge.data = null;
                    return true;
                case "UC3":
                    openWindow("/fxml/UC03AddFreelancer.fxml", "Add New Freelancer");
                    FXBridge.data = null;
                    return true;
                case "UC2":
                    openWindow("/fxml/createTask.fxml", "UC2 Create Task");
                    FXBridge.data = null;
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
