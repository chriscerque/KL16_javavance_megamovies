package net.ent.etrs.megamovies.model.entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.megamovies.commons.utils.validator.ValidatorUtils;
import net.ent.etrs.megamovies.exception.BusinessException;
import net.ent.etrs.megamovies.model.entity.references.Genre;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)

public class EntityFactory {

    public static Film fabriquerFilm(LocalDate dateSortie, String titre, Genre genre, Realisateur realisateur) throws BusinessException {
        try {
            Film film = new Film();
            film.setDateSortie(dateSortie);
            film.setTitre(titre);
            film.setGenre(genre);
            film.setRealisateur(realisateur);
            return ValidatorUtils.validate(film);
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    public static Realisateur fabriquerRealisateur(String nom) throws BusinessException {
        try {
            Realisateur realisateur = new Realisateur();
            realisateur.setNom(nom);
            return ValidatorUtils.validate(realisateur);
        } catch (Exception e) {
            throw new BusinessException(e);
        }

    }
}
