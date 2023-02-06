package net.ent.etrs.megamovies.view.references.callback;


import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.util.Callback;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class DatePickerCallBack implements Callback<DatePicker, DateCell> {

    @Override
    public DateCell call(final DatePicker datePicker) {
        return new DateCell() {
            @Override
            public void updateItem(final LocalDate item, final boolean empty) {
                super.updateItem(item, empty);

                if (item.getDayOfWeek() != DayOfWeek.WEDNESDAY) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;");
                }
            }
        };
    }
}
