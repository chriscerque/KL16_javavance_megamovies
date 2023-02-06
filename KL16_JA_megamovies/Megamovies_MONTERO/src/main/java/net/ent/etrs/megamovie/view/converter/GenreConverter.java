package net.ent.etrs.megamovie.view.converter;


import javafx.util.StringConverter;
import net.ent.etrs.megamovie.model.entities.references.Genre;

public class GenreConverter extends StringConverter<Genre> {

    @Override
    public String toString(final Genre genre) {
        return genre == null ? null : String.format("%s", genre.name().toLowerCase());
    }

    @Override
    public Genre fromString(String string) {
        return null;
    }
}
