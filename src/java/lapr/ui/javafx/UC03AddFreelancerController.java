package lapr.ui.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class UC03AddFreelancerController {
    @FXML
    public TextField txtName;
    @FXML
    public TextField txtExpertise;
    @FXML
    public TextField txtEmail;
    @FXML
    public TextField txtNIF;
    @FXML
    public TextField txtIBAN;
    @FXML
    public TextField txtAddress;
    @FXML
    public TextField txtCountry;
    @FXML
    public Button btnCancel;
    @FXML
    public Button btnCreate;

    @FXML
    public void btnCreateAction(ActionEvent actionEvent) {
        try {
            String Address = validateString(txtAddress.getText(), "Address");
            String Country = validateString(txtCountry.getText(), "Country");
            String Email = validateString(txtEmail.getText(), "Email");
            String Expertise = validateString(txtExpertise.getText(), "Expertise");
            String IBAN = validateString(txtIBAN.getText(), "IBAN");
            String Name = validateString(txtName.getText(), "Name");
            String NIF = validateString(txtNIF.getText(), "NIF");
        } catch (IllegalArgumentException ex) {
            MainJFXController.alert(ex.getMessage());
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
