package net.ent.etrs.megamovies.model.entities;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.megamovies.commons.utils.validator.ValidException;
import net.ent.etrs.megamovies.commons.utils.validator.ValidatorUtils;
import net.ent.etrs.megamovies.model.entities.references.Genre;

import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EntitiesFactory {

//    public static Vaccin fabriquerVaccin(String nom, int duree) throws ValidException {
//        return ValidatorUtils.validate(new Vaccin(duree, nom));
//    }
//
//    public static Militaire fabriquerMilitaire(String nid, String nom, String prenom, Grade grade) throws ValidException {
//        return ValidatorUtils.validate(new Militaire(nid,grade,nom,prenom));
//    }

    public static Film fabriquerFilm(LocalDate datesortie, Genre genre, String titre, Realisateur realisateur) throws ValidException {
        return ValidatorUtils.validate(new Film(datesortie, genre, titre, realisateur));
    }

    public static Realisateur fabriquerRealisateur(String nom) throws ValidException {
        return ValidatorUtils.validate(new Realisateur(nom));
    }

}
