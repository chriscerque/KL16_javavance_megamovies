package net.ent.etrs.template.controllers.converter;

import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ConverterDate extends StringConverter<LocalDate> {

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public String toString(LocalDate pdate) {
        return pdate == null ? null : pdate.format(dtf);
    }

    @Override
    public LocalDate fromString(String string) {
        return null;
    }
}
