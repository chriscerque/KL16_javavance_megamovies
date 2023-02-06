package net.ent.etrs.megamovies.model.entities;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.commons.validator.ValidException;
import net.ent.etrs.megamovies.model.entities.references.Genre;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EntitiesFactory {
    public static Film fabriquerFilm(final String titre, final Genre genre, final LocalDate dateSortie, final Realisateur realisateur)
            throws ValidException {
        return new Film(titre, genre, dateSortie, realisateur);
    }
}
