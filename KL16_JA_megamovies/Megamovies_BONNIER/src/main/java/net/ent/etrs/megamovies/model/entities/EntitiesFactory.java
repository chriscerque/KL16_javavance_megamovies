package net.ent.etrs.megamovies.model.entities;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.megamovies.commons.validator.ValidException;
import net.ent.etrs.megamovies.commons.validator.ValidatorUtils;
import net.ent.etrs.megamovies.model.entities.references.Genre;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EntitiesFactory {

    public static Film fabriquerFilm(Genre genre, String titre, LocalDate dateSortie, Realisateur realisateur) throws ValidException {
        return ValidatorUtils.validate(new Film(genre, titre, dateSortie, realisateur));
    }

    public static Realisateur fabriquerRealisateur() throws ValidException {
        return ValidatorUtils.validate(new Realisateur());
    }

}
