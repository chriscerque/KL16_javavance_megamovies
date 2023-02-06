package net.ent.etrs.template.model.dao;

import net.ent.etrs.template.model.dao.commons.BaseDao;
import net.ent.etrs.template.model.entities.Realisateur;
import net.ent.etrs.template.model.entities.references.Genre;

import java.io.Serializable;
import java.util.List;

public interface DaoRealisateur extends BaseDao<Realisateur, Serializable> {

    List<Realisateur> findByGenre(final Genre genre);

}
