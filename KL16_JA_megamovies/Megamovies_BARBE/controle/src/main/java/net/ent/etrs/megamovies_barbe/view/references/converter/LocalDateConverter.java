package net.ent.etrs.megamovies_barbe.view.references.converter;


import javafx.util.StringConverter;
import net.ent.etrs.megamovies_barbe.view.references.ConstantesView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LocalDateConverter extends StringConverter<LocalDate> {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern(ConstantesView.PATTERN_FORMAT_DATE);

    @Override
    public String toString(LocalDate date) {
        String retour;
        try {
            retour = date.format(dtf);
//            if (retour.getDayOfWeek() != DayOfWeek.WEDNESDAY) {
//                setDisable(true);
//            }
        } catch (Exception e) {
            retour = null;
        }
        return retour;
    }

    @Override
    public LocalDate fromString(String string) {
        LocalDate retour = null;
        try {
            retour = LocalDate.parse(string, dtf);
        } catch (DateTimeParseException e) {
            return null;
        }

        return retour;
    }
}
