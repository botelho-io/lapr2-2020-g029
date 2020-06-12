package lapr.ui.javafx.util;

import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public abstract class FXBridge {
    public static UC scene;
    public static Object param;

    public enum UC {
        // Main use cases
        UC1,
        UC2,
        UC3,
        UC4,
        UC5,
        UC7,
        UC8,
        UC9,
        UC11,
        UC12,

        // Unnumbered use cases
        LOGIN,
        MENU_ADMIN,
        MENU_MANAG,
        MENU_COLLA,


        // Others
        QUIT,
        MAIN_MENU,
        UC7_PART2,
        UC9_PART2,
    }

    public final static Map<UC, Pair<String, String>> UC_name_title;
    static {
        Map<UC, Pair<String, String>> tmp = new HashMap<>();
        tmp.put(UC.UC1, new Pair<>("/fxml/createTransaction.fxml", "UC1 Create Transaction"));
        tmp.put(UC.UC2, new Pair<>("/fxml/createTask.fxml", "UC2 Create Task"));
        tmp.put(UC.UC3, new Pair<>("/fxml/createFreelancer.fxml", "UC3 Create Freelancer"));
        tmp.put(UC.UC4, new Pair<>("/fxml/loadHTF.fxml", "UC4 Load Historical Transactions From File"));
        tmp.put(UC.UC5, new Pair<>("/fxml/definePaymentScheduler.fxml", "UC5 Define Payment Scheduler"));
        tmp.put(UC.UC7, new Pair<>("/fxml/mancolStatistics.fxml", "UC7 Manager/ Collaborator Statistics"));
        tmp.put(UC.UC8, new Pair<>("/fxml/createOrganization.fxml", "UC8 Create Organization"));
        tmp.put(UC.UC9, new Pair<>("/fxml/administratorStatistics.fxml", "UC9 Administrator Statistics"));
        tmp.put(UC.UC11, new Pair<>("/fxml/serializeToFile.fxml", "UC11 Serialize To File"));
        tmp.put(UC.UC12, new Pair<>("/fxml/deserializeFromFile.fxml", "UC12 Serialize From File"));
        tmp.put(UC.LOGIN, new Pair<>("/fxml/login.fxml", "Login"));
        tmp.put(UC.MENU_ADMIN, new Pair<>("/fxml/mainMenuAdmin.fxml", "Main Menu Administrator"));
        tmp.put(UC.MENU_MANAG, new Pair<>("/fxml/mainMenuManager.fxml", "Main Menu Manager"));
        tmp.put(UC.MENU_COLLA, new Pair<>("/fxml/mainMenuCollaborator.fxml", "Main Menu Collaborator"));
        tmp.put(UC.UC7_PART2, new Pair<>("/fxml/mancolStatistics_part2.fxml", "UC7 Manager/ Collaborator Statistics"));
        tmp.put(UC.UC9_PART2, new Pair<>("/fxml/administratorStatistics_part2.fxml", "UC9 Administrator Statistics"));
        UC_name_title = Collections.unmodifiableMap(tmp);
    }

    public static boolean openUC(FXBridge.UC uc, Stage stage) {
        if(uc == null) return false;
        Pair<String, String> file_name = UC_name_title.get(uc);
        if(file_name == null) {
            HelperUI.alert("Invalid UC name: " + uc.name());
            return false;
        } else {
            return HelperUI.openWindow(file_name.getKey(), file_name.getValue(), stage);
        }
    }
}
