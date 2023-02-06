package net.ent.etrs.megamovies.view.converter;

import javafx.util.StringConverter;
import net.ent.etrs.megamovies.model.entities.references.Genre;

public class GenreConverter extends StringConverter<Genre> {
	@Override
	public String toString(Genre genre) {
		return genre == null ? null : String.format("%s", (Object) Genre.values());
	}
	
	@Override
	public Genre fromString(String string) {
		return null;
	}
}
