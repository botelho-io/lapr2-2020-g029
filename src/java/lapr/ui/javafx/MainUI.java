package lapr.ui.javafx;

import autorizacao.model.SessaoUtilizador;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.util.Pair;
import lapr.controller.AppPOE;
import lapr.utils.Role;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MainUI {

    public enum UC {
        UC1,
        UC2,
        UC3,
        UC8,
        LOGIN,
        MENU_ADMIN,
        MENU_COLLA,
    }
    public final static Map<UC, Pair<String, String>> UC_name_title;
    private Stage stage;
    static {
        Map<UC, Pair<String, String>> tmp = new HashMap<>();
        tmp.put(UC.UC1, new Pair<>("/fxml/createTransaction.fxml", "UC1 Create Transaction"));
        tmp.put(UC.UC2, new Pair<>("/fxml/createTask.fxml", "UC2 Create Task"));
        tmp.put(UC.UC3, new Pair<>("/fxml/createFreelancer.fxml", "UC3 Create Freelancer"));
        tmp.put(UC.UC8, new Pair<>("/fxml/createOrganization.fxml", "UC8 Create Organization"));
        tmp.put(UC.LOGIN, new Pair<>("/fxml/login.fxml", "Login"));
        tmp.put(UC.MENU_ADMIN, new Pair<>("/fxml/mainMenuAdmin.fxml", "Main Menu Administrator"));
        tmp.put(UC.MENU_COLLA, new Pair<>("/fxml/mainMenuCollaborator.fxml", "Main Menu Collaborator"));
        UC_name_title = Collections.unmodifiableMap(tmp);
    }
    public MainUI() {
        stage = new Stage();
    }

    public static void alert(String message) {
        alert(Alert.AlertType.ERROR, message);
    }
    public static void alert(Alert.AlertType type, String message) {
        Alert a = new Alert(type, message);
        a.showAndWait();
    }
    private boolean openUC(Object uc) {
        return openUC(uc, stage);
    }
    public static boolean openUC(Object what, Stage stage) {
        if (what == null) return false;
        UC uc;
        if(what instanceof UC) uc = (UC) what;
        else if (what instanceof String) {
            String data = (String) what;
            try {
                uc = UC.valueOf(data);
            } catch (IllegalArgumentException ex) {
                alert("Unknown UC: " + data);
                return false;
            }
        } else {
            return false;
        }
        Pair<String, String> file_name = UC_name_title.get(uc);
        if(file_name == null) {
            alert("Invalid UC name: " + uc.name());
            return false;
        } else {
            return openWindow(file_name.getKey(), file_name.getValue(), stage);
        }
    }
    public static boolean openWindow(String fxml_s, String title, Stage stage) {
        Parent root;
        try {
            root = FXMLLoader.load(MainUI.class.getResource(fxml_s));
        } catch (IOException e) {
            alert("Error on fxml file: " + fxml_s + "\nError:\n" + e.getMessage());
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.exit(1);
            return false;
        }
        Scene scene = new Scene(root);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.showAndWait();
        return true;
    }

    public void start() throws Exception {
        // Login
        openUC(UC.LOGIN);
        // Was login successful?
        final SessaoUtilizador su = AppPOE.getInstance().getSessaoAtual();
        if(su == null || (!su.isLoggedIn())) {
            System.exit(0); // Not successful - Exit
        } else  do {
            // Loop trough the menus
            FXBridge.data = null;
            // Select role
            if (su.isLoggedInComPapel(Role.ADMINISTRATOR)) {
                openUC(UC.MENU_ADMIN);
            } else if (su.isLoggedInComPapel(Role.COLLABORATOR)) {
                openUC(UC.MENU_COLLA);
            } else if (su.isLoggedInComPapel(Role.MANAGER)) {
                // TODO: Complete.
            } else {
                alert("Unknown Role!");
                System.exit(1);
            }
            // Try to open selected UC
        } while (openUC(FXBridge.data));
    }
}
