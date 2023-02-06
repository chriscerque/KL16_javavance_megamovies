package net.ent.etrs.templatefx.view.converter;

import javafx.scene.control.Alert;
import javafx.util.StringConverter;
import net.ent.etrs.templatefx.view.utils.AlerteUtils;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateConverter extends StringConverter<LocalDate> {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    //TODO pattern dans la classe de constante

    @Override
    public String toString(final LocalDate localDate) {
        try {
            return localDate.format(formatter);
        } catch (DateTimeException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
            return null;
        }
    }

    @Override
    public LocalDate fromString(final String s) {
        try {
            return LocalDate.parse(s, formatter);
        } catch (DateTimeParseException e) {
            AlerteUtils.afficherExceptionDansAlerte(e, Alert.AlertType.ERROR);
            return null;
        }
    }
}
