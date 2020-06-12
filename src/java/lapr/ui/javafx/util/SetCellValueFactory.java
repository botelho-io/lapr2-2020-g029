package lapr.ui.javafx.util;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

import java.util.function.Function;

public abstract class SetCellValueFactory {
    public static<T, U> void set(TableColumn<T, U> column, Function<T, U> transform) {
        column.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<T, U>, ObservableValue<U>>() {
            @Override
            public ObservableValue<U> call(TableColumn.CellDataFeatures<T, U> dataFeatures) {
                return new ObservableValue<U>() {
                    @Override public void addListener(ChangeListener<? super U> changeListener) {
                    }
                    @Override public void removeListener(ChangeListener<? super U> changeListener) {
                    }
                    @Override public U getValue() {
                        return transform.apply(dataFeatures.getValue());
                    }
                    @Override public void addListener(InvalidationListener invalidationListener) {
                    }
                    @Override public void removeListener(InvalidationListener invalidationListener) {
                    }
                };
            }
        });
    }
}
