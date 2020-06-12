package lapr.ui.javafx;

import csvparser.LineException;
import csvparser.LineExceptionStack;
import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.LoadException;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import javafx.util.Pair;
import lapr.controller.LoadHistoricalTransactionFileController;
import lapr.model.Transaction;
import lapr.ui.javafx.util.FXBridge;
import lapr.ui.javafx.util.HelperUI;
import lapr.ui.javafx.util.SetCellValueFactory;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Function;

public class LoadHistoricalTransactionFileUI {
    @FXML
    TableView<Transaction> tableView;
    @FXML
    TableColumn<Transaction, String> transId;
    @FXML
    TableColumn<Transaction, String> tId;
    @FXML
    TableColumn<Transaction, String> tDesc;
    @FXML
    TableColumn<Transaction, Double> tDur;
    @FXML
    TableColumn<Transaction, Double> tCos;
    @FXML
    TableColumn<Transaction, String> tCat;
    @FXML
    TableColumn<Transaction, LocalDate> eFinDate;
    @FXML
    TableColumn<Transaction, Double> eDel;
    @FXML
    TableColumn<Transaction, String> eDesc;
    @FXML
    TableColumn<Transaction, String> fId;
    @FXML
    TableColumn<Transaction, String> fName;
    @FXML
    TableColumn<Transaction, String> fExp;
    @FXML
    TableColumn<Transaction, String> fMail;
    @FXML
    TableColumn<Transaction, String> fNif;
    @FXML
    TableColumn<Transaction, String> fIban;
    @FXML
    TableColumn<Transaction, String> fAdd;
    @FXML
    TableColumn<Transaction, String> fCountry;
    @FXML
    private Button btn;

    LoadHistoricalTransactionFileController ctr;
    public void initialize() throws LoadException {
        ctr = new LoadHistoricalTransactionFileController();
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All accepted files", "*.csv", "*.CSV", "*.txt", "*.TXT"),
                new FileChooser.ExtensionFilter("CSV", "*.csv", "*.CSV"),
                new FileChooser.ExtensionFilter("TXT", "*.txt", "*.TXT")
        );
        List<File> list = fc.showOpenMultipleDialog(new Stage());
        if (list == null || list.isEmpty()) {
            HelperUI.initializeExit();
        }
        try {
            ctr.addFiles(list);
        } catch (LineException le) {
            HelperUI.alert(String.format("An error occurred!\nFile: %s\nLine: %d\nErr: %s", le.getFile(), le.getLine(), le.getMessage()));
            HelperUI.initializeExit();
            return;
        } catch (IOException e) {
            HelperUI.alert(String.format("An error occurred while opening a file!\nErr: %s", e.getMessage()));
            HelperUI.initializeExit();
            return;
        }

        try {
            ctr.loadData();
        } catch (LineExceptionStack lineExceptionStack) {
            HelperUI.alert(lineExceptionStack);
        }

        // Set value factories
        SetCellValueFactory.set(transId , (Transaction::getId));
        SetCellValueFactory.set(tId     , (transaction -> transaction.getTask().getId()));
        SetCellValueFactory.set(tDesc   , (transaction -> transaction.getTask().getDescription()));
        SetCellValueFactory.set(tDur    , (transaction -> transaction.getTask().getDurationInHours()));
        SetCellValueFactory.set(tCos    , (transaction -> transaction.getTask().getCostPerHourOfJuniorEur()));
        SetCellValueFactory.set(tCat    , (transaction -> transaction.getTask().getCategory()));
        SetCellValueFactory.set(eFinDate, (transaction -> transaction.getExecutionDetails().getEndDate()));
        SetCellValueFactory.set(eDel    , (transaction -> transaction.getExecutionDetails().getHoursDelay()));
        SetCellValueFactory.set(eDesc   , (transaction -> transaction.getExecutionDetails().getDescription()));
        SetCellValueFactory.set(fId     , (transaction -> transaction.getFreelancer().getId()));
        SetCellValueFactory.set(fName   , (transaction -> transaction.getFreelancer().getName()));
        SetCellValueFactory.set(fExp    , (transaction -> transaction.getFreelancer().getLevelOfExpertise().toString()));
        SetCellValueFactory.set(fMail   , (transaction -> transaction.getFreelancer().getEmail()));
        SetCellValueFactory.set(fNif    , (transaction -> transaction.getFreelancer().getNIF()));
        SetCellValueFactory.set(fIban   , (transaction -> transaction.getFreelancer().getIBAN()));
        SetCellValueFactory.set(fAdd    , (transaction -> transaction.getFreelancer().getAddress()));
        SetCellValueFactory.set(fCountry, (transaction -> transaction.getFreelancer().getCountry()));

        List<Transaction> read = ctr.getData();
        tableView.getItems().addAll(read);
    }



    public void quit() {
        ((Stage)btn.getScene().getWindow()).close();
    }

    public void addData() {
        try {
            ctr.saveData();
        } catch (LineExceptionStack lineExceptionStack) {
            HelperUI.alert(lineExceptionStack);
        }
        HelperUI.alert(Alert.AlertType.INFORMATION, "Success!");
        quit();
    }
}
