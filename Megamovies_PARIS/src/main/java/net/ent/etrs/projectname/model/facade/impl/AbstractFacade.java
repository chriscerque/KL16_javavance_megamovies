package net.ent.etrs.projectname.model.facade.impl;

import net.ent.etrs.projectname.model.dao.FilmDao;
import net.ent.etrs.projectname.model.dao.RealisateurDao;
import net.ent.etrs.projectname.model.dao.impl.DaosFactory;

public abstract class AbstractFacade<T> {

    RealisateurDao realisateurDao = DaosFactory.fabriquerRealisateurDao();

    FilmDao filmDao = DaosFactory.fabriquerFilmDao();
}
