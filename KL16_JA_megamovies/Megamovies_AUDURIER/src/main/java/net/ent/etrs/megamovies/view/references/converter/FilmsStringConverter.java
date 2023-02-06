package net.ent.etrs.megamovies.view.references.converter;


import javafx.util.StringConverter;
import net.ent.etrs.megamovies.model.entities.Film;

public class FilmsStringConverter extends StringConverter<Film> {

    @Override
    public String toString(final Film film) {
        return film == null ? null : String.format("%s %s", film.getTitre(), film.getDateSortie());
    }

    @Override
    public Film fromString(final String s) {
        return null;
    }
}
