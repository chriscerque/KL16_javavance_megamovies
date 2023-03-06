package net.ent.etrs.projectname.model.entities;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.projectname.commons.validator.ValidException;
import net.ent.etrs.projectname.commons.validator.ValidatorUtils;
import net.ent.etrs.projectname.model.entities.reference.Genre;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EntitiesFactory {

    public static Film fabriquerFilm(LocalDate dateSortie, Genre genre, String titre, Realisateur realisateur) throws ValidException {
        return ValidatorUtils.validate(new Film(dateSortie, genre, titre, realisateur));
    }

    public static Realisateur fabriquerRealisateur(String nom) throws ValidException {
        return ValidatorUtils.validate(new Realisateur(nom));
    }

}
