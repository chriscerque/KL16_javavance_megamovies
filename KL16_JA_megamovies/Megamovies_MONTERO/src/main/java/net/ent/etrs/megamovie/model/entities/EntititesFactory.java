package net.ent.etrs.megamovie.model.entities;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.megamovie.commons.validator.ValidException;
import net.ent.etrs.megamovie.commons.validator.ValidatorUtils;
import net.ent.etrs.megamovie.model.entities.references.Genre;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EntititesFactory {

    public static Film fabriquerFilm(final LocalDate dateSortie, final Genre genre, final String titre, final Realisateur realisateur) throws ValidException {
        Film film = new Film();
        film.setDateSortie(dateSortie);
        film.setGenre(genre);
        film.setTitre(titre);
        film.setRealisateur(realisateur);
        return ValidatorUtils.validate(film);
    }


    public static Realisateur fabriquerRealisateur(final String nom) throws ValidException {
        Realisateur realisateur = new Realisateur();
        realisateur.setNom(nom);
        return ValidatorUtils.validate(realisateur);
    }
}
