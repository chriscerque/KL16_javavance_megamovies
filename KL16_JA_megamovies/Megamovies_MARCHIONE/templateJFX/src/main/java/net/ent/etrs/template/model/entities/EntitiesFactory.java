package net.ent.etrs.template.model.entities;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.template.model.commons.validator.ValidException;
import net.ent.etrs.template.model.commons.validator.ValidatorUtils;
import net.ent.etrs.template.model.entities.references.Genre;
import net.ent.etrs.template.model.entities.references.Realisateur;

import javax.persistence.SecondaryTables;
import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EntitiesFactory<T extends AbstractEntity> {




    public static Film fabriquerFilm(LocalDate dateSortie, String titre, String nom, Genre genre, Realisateur realisateur) throws ValidException {
        return ValidatorUtils.validate(new Film(dateSortie, nom, titre,genre, realisateur));
    }


    public static Realisateur fabriquerRealisateur(String nom, Set<Genre> setGenres) throws ValidException {
        return ValidatorUtils.validate(new Realisateur(nom, setGenres));
    }







}
