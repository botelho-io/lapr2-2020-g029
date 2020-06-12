package lapr.ui.javafx.util;

import csvparser.LineExceptionStack;
import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadException;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class HelperUI {
    public static void alert(String message) {
        System.out.println(message);
        alert(Alert.AlertType.ERROR, message);
    }

    public static void alert(Alert.AlertType type, String message) {
        Alert a = new Alert(type, message);
        a.showAndWait();
    }

    public static void alert(LineExceptionStack message) {
        Object tmp = FXBridge.param;
        FXBridge.param = (Object) message;
        openWindow("/fxml/lineExceptionStackAlert.fxml", "Alert!", new Stage());
        FXBridge.param = tmp;
    }

    public static void initializeExit() {
        throw new IllegalStateException("___EXIT_UC___");
    }

    public static boolean openWindow(String fxml_s, String title, Stage stage) {
        Parent root;
        try {
            root = FXMLLoader.load(MainUI.class.getResource(fxml_s));
        } catch (Exception e) {
            // HACK to exit early out of initialization
            // TODO: Think of something better?
            if(e instanceof LoadException)
                if(e.getCause() instanceof InvocationTargetException)
                    if(e.getCause().getCause() instanceof IllegalStateException)
                        if(e.getCause().getCause().getMessage().equals("___EXIT_UC___"))
                            return true;
            // Actually handle exceptions
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
