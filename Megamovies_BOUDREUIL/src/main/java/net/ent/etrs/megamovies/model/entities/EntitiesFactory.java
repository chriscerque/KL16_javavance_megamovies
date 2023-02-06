package net.ent.etrs.megamovies.model.entities;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.megamovies.commons.validator.ValidException;
import net.ent.etrs.megamovies.commons.validator.ValidatorUtils;
import net.ent.etrs.megamovies.model.entities.references.Genre;

import java.time.LocalDate;


@NoArgsConstructor(access=AccessLevel.PRIVATE)
public final class EntitiesFactory {

    public static Film getFilm(final String titre, final Genre genre, final Realisateur realisateur, final LocalDate dateSortie) throws ValidException {
        return ValidatorUtils.validate(new Film(titre, dateSortie, genre, realisateur));
    }

    public static Realisateur getRealisateur(final String nom) throws ValidException {
        return ValidatorUtils.validate(new Realisateur(nom));
    }
}
