package net.ent.etrs.megamovies_falgat.model.facades;

import net.ent.etrs.megamovies_falgat.model.entities.Film;
import net.ent.etrs.megamovies_falgat.model.entities.references.Genre;
import net.ent.etrs.megamovies_falgat.model.exceptions.BusinessException;

import java.util.List;

public interface FacadeFilm {

    List<Film> readAllFilms() throws BusinessException;

    List<Film> readAllFilmsByGenre(Genre genre) throws BusinessException;

    void save(Film film) throws BusinessException;

    void delete(Long id) throws BusinessException;
}
