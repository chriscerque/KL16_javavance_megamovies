package net.ent.etrs.megamovies_barbe.model.entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.megamovies_barbe.commons.validator.ValidException;
import net.ent.etrs.megamovies_barbe.commons.validator.ValidatorUtils;
import net.ent.etrs.megamovies_barbe.model.entity.references.Genre;

import java.time.LocalDate;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EntitiesFactory {

    public static Film fabriquerFilm(final LocalDate dateSortie, final Genre genre, final String titre,
                                     final Realisateur realisateur) throws ValidException {
        try {
            Film film = new Film();
            film.setDateSortie(dateSortie);
            film.setGenre(genre);
            film.setTitre(titre);
            film.setRealisateur(realisateur);
            return ValidatorUtils.validate(film);
        } catch (ValidException e) {
            throw new ValidException(e.getMessage());
        }
    }

    public static Realisateur fabriquerRealisateur(final String nom, final Genre genres) throws ValidException {
        try {
            Realisateur realisateur = new Realisateur();
            realisateur.setNom(nom);
            realisateur.ajouterGenre(genres);
            return ValidatorUtils.validate(realisateur);
        } catch (ValidException e) {
            throw new ValidException(e.getMessage());
        }
    }

}
