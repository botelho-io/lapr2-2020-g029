package lapr.ui.javafx.util;

import authorization.model.UserSession;
import javafx.stage.Stage;
import lapr.controller.AppPOE;

import java.io.IOException;

public class MainUI {
    private Stage stage;

    public MainUI() {
        stage = new Stage();
    }
    private boolean openState(FXBridge.STATE state) {
        return FXBridge.openState(state, stage);
    }

    public void mainMenu() {
        final UserSession su = AppPOE.getInstance().getApp().getAuthFacade().getCurrentSession();
        // Loop trough the menus
        FXBridge.scene = null;
        switch (su.getRoleUser()) {
            case ADMINISTRATOR:
                openState(FXBridge.STATE.MENU_ADMIN);
                break;
            case COLLABORATOR:
                openState(FXBridge.STATE.MENU_COLLA);
                break;
            case MANAGER:
                openState(FXBridge.STATE.MENU_MANAG);
                break;
            default:
                HelperUI.alert("Unknown Role!");
                System.exit(1);
        }
    }

    public void start() {
        // TODO: Delete
        //AppPOE.getInstance().getApp().getAuthFacade().doLogin("colab@dei.pt", "password");

        // Load data from file
        openState(FXBridge.STATE.UC12);

        // Login
        openState(FXBridge.STATE.LOGIN);
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
                    FXBridge.STATE STATE;
                    do {
                        STATE = FXBridge.scene;
                        FXBridge.scene = null;
                    } while(openState(STATE));
                } else {
                    // Nothing selected. Quit?
                    openState(FXBridge.STATE.UC11);
                    if(FXBridge.scene == null)
                        FXBridge.scene = FXBridge.STATE.MAIN_MENU;
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
