package net.ent.etrs.megamovies_falgat.model.facades.impl;


import net.ent.etrs.megamovies_falgat.model.dao.DaoFilm;
import net.ent.etrs.megamovies_falgat.model.dao.DaoRealisateur;
import net.ent.etrs.megamovies_falgat.model.dao.impl.DaoFactory;

public abstract class AbstractFacade<T> {

    DaoFilm daoFilm = DaoFactory.getDaoFilm();

    DaoRealisateur daoRealisateur = DaoFactory.getDaoRealisateur();




}
