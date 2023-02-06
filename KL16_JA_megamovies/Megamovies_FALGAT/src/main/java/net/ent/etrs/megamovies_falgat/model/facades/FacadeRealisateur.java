package net.ent.etrs.megamovies_falgat.model.facades;

import net.ent.etrs.megamovies_falgat.model.entities.Realisateur;
import net.ent.etrs.megamovies_falgat.model.entities.references.Genre;
import net.ent.etrs.megamovies_falgat.model.exceptions.BusinessException;

import java.util.List;

public interface FacadeRealisateur {

    List<Realisateur> readAllRealisateur() throws BusinessException;

    List<Realisateur> readAllRealisateurByGenre(Genre genre) throws BusinessException;


}
