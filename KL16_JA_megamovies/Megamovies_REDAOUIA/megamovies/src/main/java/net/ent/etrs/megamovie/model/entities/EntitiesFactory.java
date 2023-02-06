package net.ent.etrs.megamovie.model.entities;

import java.time.LocalDate;

public class EntitiesFactory {
    public static Film fabriquerFilm(LocalDate dateSortie, String titre, Genre genre, Realisateur realisateur) {
        return new Film(dateSortie, titre, genre, realisateur);

    }
}
