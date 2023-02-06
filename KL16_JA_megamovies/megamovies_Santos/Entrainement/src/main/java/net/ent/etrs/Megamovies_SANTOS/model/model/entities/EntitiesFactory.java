package net.ent.etrs.Megamovies_SANTOS.model.model.entities;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.Megamovies_SANTOS.model.model.commons.validator.ValidException;
import net.ent.etrs.Megamovies_SANTOS.model.model.commons.validator.ValidatorUtils;


import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EntitiesFactory {

    public static Realisateur fabriquerRealisateur(String nom, Set<Genre> genres) throws ValidException {
        return ValidatorUtils.validate(new Realisateur(nom));
    }

    public static Film fabriquerFilm(LocalDate dateSortie, Genre genre , String titre , Realisateur realisateur) throws ValidException {
        return ValidatorUtils.validate(new Film(titre,dateSortie,genre,realisateur));
    }




}
