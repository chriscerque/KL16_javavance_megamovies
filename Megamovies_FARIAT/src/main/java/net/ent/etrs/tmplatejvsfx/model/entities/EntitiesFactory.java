package net.ent.etrs.tmplatejvsfx.model.entities;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.tmplatejvsfx.commons.validator.ValidException;
import net.ent.etrs.tmplatejvsfx.commons.validator.ValidatorUtils;
import net.ent.etrs.tmplatejvsfx.model.entities.reference.Genre;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EntitiesFactory {

    public static Film fabriquerFilm(LocalDate dateSortie, Genre genre, String titre, Realisateur realisateur) throws ValidException {
        Film f = new Film();
        f.setDateSortie(dateSortie);
        f.setTitre(titre);
        f.setGenre(genre);
        f.setTitre(titre);
        f.setRealisateur(realisateur);
        return ValidatorUtils.validate(f);
    }

    public static Realisateur fabriquerRealisateur(String nom) throws ValidException {
        Realisateur r = new Realisateur();
        r.setNom(nom);
        return ValidatorUtils.validate(r);
    }


}
