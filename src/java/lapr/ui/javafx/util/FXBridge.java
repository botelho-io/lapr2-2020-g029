package lapr.ui.javafx.util;

import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class FXBridge {
    public static STATE scene;
    public static Object param;

    public enum STATE {
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

    public final static Map<STATE, Pair<String, String>> stateNameTitle;
    static {
        Map<STATE, Pair<String, String>> tmp = new HashMap<>();
        tmp.put(STATE.UC1, new Pair<>("/fxml/createTransaction.fxml", "UC1 Create Transaction"));
        tmp.put(STATE.UC2, new Pair<>("/fxml/createTask.fxml", "UC2 Create Task"));
        tmp.put(STATE.UC3, new Pair<>("/fxml/createFreelancer.fxml", "UC3 Create Freelancer"));
        tmp.put(STATE.UC4, new Pair<>("/fxml/loadHTF.fxml", "UC4 Load Historical Transactions From File"));
        tmp.put(STATE.UC5, new Pair<>("/fxml/definePaymentScheduler.fxml", "UC5 Define Payment Scheduler"));
        tmp.put(STATE.UC7, new Pair<>("/fxml/mancolStatistics.fxml", "UC7 Manager/ Collaborator Statistics"));
        tmp.put(STATE.UC8, new Pair<>("/fxml/createOrganization.fxml", "UC8 Create Organization"));
        tmp.put(STATE.UC9, new Pair<>("/fxml/administratorStatistics.fxml", "UC9 Administrator Statistics"));
        tmp.put(STATE.UC11, new Pair<>("/fxml/serializeToFile.fxml", "UC11 Serialize To File"));
        tmp.put(STATE.UC12, new Pair<>("/fxml/deserializeFromFile.fxml", "UC12 Serialize From File"));
        tmp.put(STATE.LOGIN, new Pair<>("/fxml/login.fxml", "Login"));
        tmp.put(STATE.MENU_ADMIN, new Pair<>("/fxml/mainMenuAdmin.fxml", "Main Menu Administrator"));
        tmp.put(STATE.MENU_MANAG, new Pair<>("/fxml/mainMenuManager.fxml", "Main Menu Manager"));
        tmp.put(STATE.MENU_COLLA, new Pair<>("/fxml/mainMenuCollaborator.fxml", "Main Menu Collaborator"));
        tmp.put(STATE.UC7_PART2, new Pair<>("/fxml/mancolStatistics_part2.fxml", "UC7 Manager/ Collaborator Statistics"));
        tmp.put(STATE.UC9_PART2, new Pair<>("/fxml/administratorStatistics_part2.fxml", "UC9 Administrator Statistics"));
        stateNameTitle = Collections.unmodifiableMap(tmp);
    }

    public static boolean openState(STATE state, Stage stage) {
        if(state == null) return false;
        Pair<String, String> file_name = stateNameTitle.get(state);
        if(file_name == null) {
            HelperUI.alert("Invalid file name: " + state.name());
            return false;
        } else {
            return HelperUI.openWindow(file_name.getKey(), file_name.getValue(), stage);
        }
    }
}
