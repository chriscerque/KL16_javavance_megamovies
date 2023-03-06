package net.ent.etrs.projectname.model.dao;

import net.ent.etrs.projectname.model.entities.Realisateur;
import net.ent.etrs.projectname.model.entities.reference.Genre;

import java.util.Set;

public interface RealisateurDao extends BaseDao<Realisateur, Long>{

    Set<Realisateur> findByGenre(Genre genre);


    Set<Realisateur> readAll();
}
