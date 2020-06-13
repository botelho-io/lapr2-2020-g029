package lapr.ui.javafx;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;
import lapr.controller.AdministratorStatisticsController;
import lapr.model.Freelancer;
import lapr.ui.javafx.util.FXBridge;
import lapr.ui.javafx.util.HelperUI;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class AdministratorStatisticsUI {
    @FXML
    public ListView<Freelancer> lvNSelected;

    @FXML
    public ListView<Freelancer> lvSelected;

    AdministratorStatisticsController ctr;
    @FXML
    public void initialize() {
        lvSelected.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        lvNSelected.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        ctr = new AdministratorStatisticsController();
        lvNSelected.getItems().addAll(ctr.getFreelancers());
    }

    @FXML
    public void quit(ActionEvent actionEvent) {
        ((Stage)lvNSelected.getScene().getWindow()).close();
    }

    @FXML
    public void next(ActionEvent actionEvent) {
        final ObservableList<Freelancer> s = lvSelected.getItems();
        final Set<Freelancer> selected = new HashSet<>(s); // Avoid syc problems...
        if(!ctr.setFreelancers(selected)) {
            HelperUI.alert("The freelancers selected do not have anny transactions.");
        } else {
            FXBridge.scene = FXBridge.STATE.UC9_PART2;
            FXBridge.param = (Object) ctr;
            quit(null);
        }
    }

    @FXML
    public void selectAll(ActionEvent actionEvent) {
        moveAll(lvNSelected, lvSelected);
    }

    @FXML
    public void deselectAll(ActionEvent actionEvent) {
        moveAll(lvSelected, lvNSelected);
    }

    private<T> void moveAll(final ListView<T> from, final ListView<T> to) {
        to.getItems().addAll(from.getItems());
        from.getItems().clear();
    }

    @FXML
    public void select(ActionEvent actionEvent) {
        move(lvNSelected, lvSelected);
    }

    @FXML
    public void deselect(ActionEvent actionEvent) {
        move(lvSelected, lvNSelected);
    }

    private<T> void move(final ListView<T> from, final ListView<T> to) {
        final ObservableList<T> s = from.getSelectionModel().getSelectedItems();
        final ArrayList<T> selected = new ArrayList<>(s); // Avoid syc problems...
        from.getItems().removeAll(selected);
        to.getItems().addAll(selected);
    }
}
