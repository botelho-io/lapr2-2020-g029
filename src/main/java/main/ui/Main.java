/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main.ui;

import javafx.application.Application;
import javafx.stage.Stage;
import main.ui.javafx.MainJFXController;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        MainJFXController ctrl = new MainJFXController();
        ctrl.runApp();
    }
}
