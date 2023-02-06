package net.ent.etrs.megamovies_pelloquet.model.dao;


import net.ent.etrs.megamovies_pelloquet.model.dao.base.BaseDao;
import net.ent.etrs.megamovies_pelloquet.model.entities.Realisateur;
import net.ent.etrs.megamovies_pelloquet.model.entities.references.Genre;

import java.io.Serializable;
import java.util.Set;

public interface RealisateurDao extends BaseDao<Realisateur, Serializable> {
    Set<Realisateur> searchRealisateurByGenre(Genre genre);
}
