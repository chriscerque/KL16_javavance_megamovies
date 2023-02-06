package net.ent.etrs.template.controllers.converter;

import java.time.DayOfWeek;
import java.time.LocalDate;

import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class ConverterDatePicker extends StringConverter<LocalDate> implements Callback<DatePicker, DateCell> {

    final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {

        @Override
        public DateCell call(final DatePicker datePicker) {
            return new DateCell() {
                @Override
                public void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);

                    // Disable Monday, Tueday, Wednesday.
                    if (item.getDayOfWeek() == DayOfWeek.MONDAY //
                            || item.getDayOfWeek() == DayOfWeek.TUESDAY //
                            || item.getDayOfWeek() == DayOfWeek.FRIDAY //
                            || item.getDayOfWeek() == DayOfWeek.SATURDAY //
                            || item.getDayOfWeek() == DayOfWeek.SUNDAY //
                            || item.getDayOfWeek() == DayOfWeek.THURSDAY) {
                        setDisable(true);
                        setStyle("-fx-background-color: #ffc0cb;");
                    }
                }
            };
        }
    };

    @Override
    public String toString(LocalDate date) {
        return null;
    }

    @Override
    public LocalDate fromString(String s) {
        return null;
    }

    @Override
    public DateCell call(DatePicker datePicker) {
        return new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);

                // Disable Monday, Tueday, Wednesday.
                if (item.getDayOfWeek() == DayOfWeek.MONDAY //
                        || item.getDayOfWeek() == DayOfWeek.TUESDAY //
                        || item.getDayOfWeek() == DayOfWeek.FRIDAY //
                        || item.getDayOfWeek() == DayOfWeek.SATURDAY //
                        || item.getDayOfWeek() == DayOfWeek.SUNDAY //
                        || item.getDayOfWeek() == DayOfWeek.THURSDAY) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;");
                }
            }
        };
    }
    }

