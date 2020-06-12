package lapr.ui.javafx.util;

import authorization.model.UserSession;
import javafx.stage.Stage;
import lapr.controller.AppPOE;
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
        final UserSession su = AppPOE.getInstance().getApp().getAuthFacade().getCurrentSession();
        // Loop trough the menus
        FXBridge.scene = null;
        // Select role
        if (su.isLoggedInWithRole(Role.ADMINISTRATOR)) {
            openUC(FXBridge.UC.MENU_ADMIN);
        } else if (su.isLoggedInWithRole(Role.COLLABORATOR)) {
            openUC(FXBridge.UC.MENU_COLLA);
        } else if (su.isLoggedInWithRole(Role.MANAGER)) {
            openUC(FXBridge.UC.MENU_MANAG);
        } else {
            HelperUI.alert("Unknown Role!");
            System.exit(1);
        }
    }

    public void start() {
        AppPOE.getInstance().getApp().getAuthFacade().doLogin("colab@dei.pt", "password");

        // Load data from file
        //openUC(FXBridge.UC.UC12);

        // Login
        //openUC(FXBridge.UC.LOGIN);
        // Was login successful?
        final UserSession su = AppPOE.getInstance().getApp().getAuthFacade().getCurrentSession();
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
                    if(FXBridge.scene == null)
                        FXBridge.scene = FXBridge.UC.MAIN_MENU;
                    switch (FXBridge.scene) {
                        case MAIN_MENU: continue MAIN_MENU_LOOP;
                        case QUIT: break MAIN_MENU_LOOP;
                    }
                }
            }


        }

        try {
            AppPOE.getInstance().getApp().close();
        } catch (IOException e) {
            HelperUI.alert("Error while closing the app:\n"+e.getMessage());
        }
    }
}
