package lapr.ui.javafx;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lapr.controller.CreateFreelancerController;

public class CreateFreelancerUI {
    @FXML
    public TextField txtName;
    @FXML
    public ChoiceBox<String> ddExpertise;
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

    CreateFreelancerController controller;
    @FXML
    public void initialize() {
        controller = new CreateFreelancerController();
        ddExpertise.setItems(FXCollections.observableArrayList(controller.getValidExpertise()));
        txtAddress.setText("");
        txtCountry.setText("");
        txtEmail.setText("");
        txtIBAN.setText("");
        txtName.setText("");
        txtNIF.setText("");
    }

    @FXML
    public void btnCreateAction(ActionEvent actionEvent) {
        String Address;
        String Country;
        String Email;
        String Expertise;
        String IBAN;
        String Name;
        String NIF;
        try {
            Address = validateString(txtAddress.getText(), "Address");
            Country = validateString(txtCountry.getText(), "Country");
            Email = validateString(txtEmail.getText(), "Email");
            Expertise = validateString(ddExpertise.getValue(), "Expertise");
            IBAN = validateString(txtIBAN.getText(), "IBAN");
            Name = validateString(txtName.getText(), "Name");
            NIF = validateString(txtNIF.getText(), "NIF");
        } catch (IllegalArgumentException ex) {
            MainUI.alert(ex.getMessage());
            return;
        }
        try {
            if(!controller.newFreelancer(Name, Expertise, Email, NIF, IBAN, Address, Country)) {
                throw new IllegalStateException("The Freelancer couldn't be created.\nAnother freelancer in the system already has that NIF/ IBAN/ Email!");
            }else if(!controller.addFreelancer()) {
                throw new IllegalStateException("The Freelancer couldn't be created.\nAnother freelancer in the system already has that NIF/ IBAN/ Email!");
            } else {
                MainUI.alert(Alert.AlertType.INFORMATION, "Success!\nYou may keep adding freelancers or quit.");
                initialize();
            }
        } catch (IllegalArgumentException | IllegalStateException e) {
            MainUI.alert(e.getMessage());
        }
    }

    @FXML
    public void btnCancelAction(ActionEvent actionEvent) {
        ((Stage)btnCancel.getScene().getWindow()).close();
    }

    private String validateString(String data, String dataName) {
        if(data == null || (data = data.trim()).isEmpty()) {
            throw new IllegalArgumentException(dataName + " cannot be empty!");
        } else return data;
    }
}
