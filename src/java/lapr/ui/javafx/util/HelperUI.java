package lapr.ui.javafx.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class HelperUI {
    public static void alert(String message) {
        System.out.println(message);
        alert(Alert.AlertType.ERROR, message);
    }

    public static void alert(Alert.AlertType type, String message) {
        Alert a = new Alert(type, message);
        a.showAndWait();
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
}
