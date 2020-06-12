package lapr.ui.javafx;

import csvparser.LineException;
import csvparser.LineExceptionStack;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lapr.ui.javafx.util.FXBridge;
import lapr.ui.javafx.util.HelperUI;
import lapr.ui.javafx.util.SetCellValueFactory;

import java.util.ArrayList;

public class LineExceptionStackAlertUI {
    @FXML
    public Label lblMSG;
    @FXML
    public TableView<LineException> tvErr;
    @FXML
    public TableColumn<LineException, String> colFile;
    @FXML
    public TableColumn<LineException, Integer> colLine;
    @FXML
    public TableColumn<LineException, String> colErr;

    @FXML
    public void initialize() {
        if(!(FXBridge.param instanceof LineExceptionStack)) {
            HelperUI.alert("Error on FXBridge");
            HelperUI.initializeExit();
        }
        SetCellValueFactory.set(colErr, LineException::getMessage);
        SetCellValueFactory.set(colFile, LineException::getFile);
        SetCellValueFactory.set(colLine, LineException::getLine);
        LineExceptionStack stack = (LineExceptionStack) FXBridge.param;
        tvErr.setItems(FXCollections.observableArrayList(stack.getStack()));
        lblMSG.setText(stack.getMessage());
    }

    @FXML
    public void quit() {
        ((Stage)lblMSG.getScene().getWindow()).close();
    }
}
