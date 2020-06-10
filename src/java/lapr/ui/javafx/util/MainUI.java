package lapr.ui.javafx.util;

import autorizacao.model.SessaoUtilizador;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.util.Pair;
import lapr.controller.AppPOE;
import lapr.ui.javafx.util.FXBridge;
import lapr.ui.javafx.util.HelperUI;
import lapr.utils.Constants;
import lapr.utils.Role;

import java.io.IOException;

public class MainUI {
    private Stage stage;

    public MainUI() {
        stage = new Stage();
    }
    private boolean openUC(FXBridge.UC uc) {
        return FXBridge.openUC(uc, stage);
    }

    public void mainMenu() {
        final SessaoUtilizador su = AppPOE.getInstance().getApp().getAuthFacade().getSessaoAtual();
        // Loop trough the menus
        FXBridge.scene = null;
        // Select role
        if (su.isLoggedInComPapel(Role.ADMINISTRATOR)) {
            openUC(FXBridge.UC.MENU_ADMIN);
        } else if (su.isLoggedInComPapel(Role.COLLABORATOR)) {
            openUC(FXBridge.UC.MENU_COLLA);
        } else if (su.isLoggedInComPapel(Role.MANAGER)) {
            openUC(FXBridge.UC.MENU_MANAG);
        } else {
            HelperUI.alert("Unknown Role!");
            System.exit(1);
        }
    }

    public void start() throws Exception {
        // Load data from file
        openUC(FXBridge.UC.UC12);

        // Login
        openUC(FXBridge.UC.LOGIN);
        // Was login successful?
        final SessaoUtilizador su = AppPOE.getInstance().getApp().getAuthFacade().getSessaoAtual();
        if(su == null || (!su.isLoggedIn())) {
            System.exit(0); // Not successful - Exit
        } else {

            // Main interaction loop
            MAIN_MENU_LOOP:
            while (true) {
                // Pull scene from main menu
                mainMenu();

                // Check is a scene was selected
                if(FXBridge.scene != null) {
                    // It was.
                    // Continue opening UC panels until there are no more panels to open;
                    // This allows a panel to just set a FXBridge.scene and it will be automatically
                    // opened when the current UC closes;
                    // FXBridge.param may be used for iter-panel communications.
                    FXBridge.UC uc;
                    do {
                        uc = FXBridge.scene;
                        FXBridge.scene = null;
                    } while(openUC(uc));
                } else {
                    // Nothing selected. Quit?
                    openUC(FXBridge.UC.UC11);
                    switch (FXBridge.scene) {
                        case QUIT: break MAIN_MENU_LOOP;
                        case MAIN_MENU: continue MAIN_MENU_LOOP;
                    }
                }
            }


        }
    }
}
