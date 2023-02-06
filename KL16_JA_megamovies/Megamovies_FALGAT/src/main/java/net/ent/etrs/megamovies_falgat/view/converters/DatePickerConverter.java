package net.ent.etrs.megamovies_falgat.view.converters;

import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.util.Callback;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class DatePickerConverter {
    // Factory to create Cell of DatePicker
    public static Callback<DatePicker, DateCell> getDayCellFactory() {

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
                                || item.getDayOfWeek() == DayOfWeek.THURSDAY
                                || item.getDayOfWeek() == DayOfWeek.FRIDAY
                                || item.getDayOfWeek() == DayOfWeek.SUNDAY
                                || item.getDayOfWeek() == DayOfWeek.SATURDAY) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                };
            }
        };
        return dayCellFactory;
    }
}
