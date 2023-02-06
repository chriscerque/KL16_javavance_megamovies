package net.ent.etrs.megamovies.view.converters;

import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateJFXConverter extends StringConverter<LocalDate> {

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Method to convert a Person-Object to a String
    @Override
    public String toString(LocalDate pdate) {
        return pdate == null ? null : pdate.format(dtf);
    }

    @Override
    public LocalDate fromString(String string) {
        return null;
    }
}
