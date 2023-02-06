package net.ent.etrs.megamovies.model.entities;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.megamovies.model.entities.references.Genre;

import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EntitiesFactory {
    public static Realisateur fabriquerRealisateur(String nom, Set<Genre> genres) {
        Realisateur realisateur =  new Realisateur();
        realisateur.setNom(nom);
        realisateur.getGenres().addAll(genres);
        return realisateur;
    }

    public static Film fabriquerFilm(String titre, Realisateur realisateur, LocalDate dateSortie, Genre genre) {
        Film film =  new Film();
        film.setTitre(titre);
        film.setRealisateur(realisateur);
        film.setDateSortie(dateSortie);
        film.setGenre(genre);
        return film;
    }
}
