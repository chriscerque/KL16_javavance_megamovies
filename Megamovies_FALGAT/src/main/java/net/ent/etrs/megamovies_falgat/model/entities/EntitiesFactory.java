package net.ent.etrs.megamovies_falgat.model.entities;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.megamovies_falgat.commons.validator.ValidException;
import net.ent.etrs.megamovies_falgat.commons.validator.ValidatorUtils;
import net.ent.etrs.megamovies_falgat.model.entities.references.Genre;

import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EntitiesFactory {

    public static Realisateur fabriquerRealisateur(final String nom, final Set<Genre> genres) throws ValidException {
        Realisateur r = new Realisateur();
        r.setNom(nom);
        r.ajouterGenres(genres);
        return ValidatorUtils.validate(r);

    }

    public static Film fabriquerFilm(final String titre, final Genre genre, final LocalDate dateSortie, final Realisateur realisateur) throws ValidException {
        Film f = new Film();
        f.setGenre(genre);
        f.setRealisateur(realisateur);
        f.setTitre(titre);
        f.setDateSortie(dateSortie);
        return ValidatorUtils.validate(f);
    }



}
