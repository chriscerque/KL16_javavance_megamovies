package net.ent.etrs.megamovie.view.converter;

import javafx.util.StringConverter;
import net.ent.etrs.megamovie.view.references.ConstantesView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateJfxConverter extends StringConverter<LocalDate> {


    DateTimeFormatter dtf = DateTimeFormatter.ofPattern(ConstantesView.PATTERN_DATE);

    @Override
    public String toString(LocalDate d) {
        return d == null ? null : d.format(dtf);
    }

    @Override
    public LocalDate fromString(String string) {
        return string == null ? null : LocalDate.parse(string);
    }
}
