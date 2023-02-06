package net.ent.etrs.Megamovies_SANTOS.model.model.facades;



import net.ent.etrs.Megamovies_SANTOS.model.model.entities.Genre;
import net.ent.etrs.Megamovies_SANTOS.model.model.entities.Realisateur;
import net.ent.etrs.Megamovies_SANTOS.model.model.exceptions.BusinessException;

import java.util.List;
import java.util.Set;

public interface FacadeMetierRealisateur {

    List<Realisateur> readAll() throws BusinessException;

    Realisateur save(final Realisateur realisateur) throws BusinessException;

    void delete(final Realisateur realisateur) throws BusinessException;

    Set<Realisateur> findByGenre(final Genre genre) throws BusinessException;
}
