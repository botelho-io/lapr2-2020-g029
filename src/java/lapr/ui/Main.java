/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lapr.ui;

import javafx.application.Application;
import javafx.stage.Stage;
import lapr.ui.javafx.util.MainUI;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        MainUI ctrl = new MainUI();
        ctrl.start();
    }
}
