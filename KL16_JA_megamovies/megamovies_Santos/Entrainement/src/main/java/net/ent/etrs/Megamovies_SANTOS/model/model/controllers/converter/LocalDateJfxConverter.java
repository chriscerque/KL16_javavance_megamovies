package net.ent.etrs.Megamovies_SANTOS.model.model.controllers.converter;

import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateJfxConverter extends StringConverter<LocalDate> {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Override
    public String toString(LocalDate d) {
        try {


            return d.format(dtf);
        } catch (Exception e) {
            return "";
        }
    }

    @Override
    public LocalDate fromString(String string) {
        return null;
    }
}


