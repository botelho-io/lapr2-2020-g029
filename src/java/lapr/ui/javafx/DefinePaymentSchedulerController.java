package lapr.ui.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lapr.controller.DefineTimeController;

import java.time.LocalTime;

public class DefinePaymentSchedulerController {

        @FXML
        public TextField fieldTimeOfDay;
        @FXML
        public TextField fieldDayOfMonth;

        @FXML
        public Button btnSet;
        @FXML
        public Button btnCancel;


        @FXML
        public void btnSetAction (ActionEvent actionEvent) {
            try {
                final String timeOfDay = validateString(fieldTimeOfDay.getText(), "TimeOfDay");
                final String dayOfMonth = validateString(fieldDayOfMonth.getText(), "DayOfMonth");
                final boolean succsess = DefineTimeController.getInstace.newPaymentScheduler( Integer.parseInt(dayOfMonth), LocalTime.parse(timeOfDay));
                if(succsess) {
                    ((Stage) fieldDayOfMonth.getScene().getWindow()).close();
                } else {
                    Alert a = new Alert(Alert.AlertType.ERROR, "Scheduler with wrong data!!!");
                    a.show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @FXML
        public void btnCancelAction(ActionEvent actionEvent) {
        }

        private String validateString(String data, String dataName) {
            if(data == null || (data = data.trim()).isEmpty()) {
                throw new IllegalArgumentException(dataName + " cannot be empty!");
            } else return data;
        }


    }
