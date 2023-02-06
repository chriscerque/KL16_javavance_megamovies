package net.ent.etrs.template.model.facades.impl;


import net.ent.etrs.template.model.dao.DaoFilm;
import net.ent.etrs.template.model.dao.DaoRealisateur;
import net.ent.etrs.template.model.dao.impl.DaoFactory;

public abstract class AbstractFacade<T> {

    DaoRealisateur daoRealisateur = DaoFactory.fabriquerRealisateur();
    DaoFilm daoFilm = DaoFactory.fabriquerFilm();



}
