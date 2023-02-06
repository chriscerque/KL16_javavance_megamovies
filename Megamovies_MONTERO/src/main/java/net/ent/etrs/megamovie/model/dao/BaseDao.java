package net.ent.etrs.megamovie.model.dao;


import net.ent.etrs.megamovie.model.dao.exceptions.DaoException;
import net.ent.etrs.megamovie.model.entities.AbstractEntity;

import java.io.Serializable;
import java.util.Optional;

public interface BaseDao<T extends AbstractEntity, ID extends Serializable> {

    Optional<T> find(ID id) throws DaoException;

    Iterable<T> findAll() throws DaoException;

    Optional<T> save(T entity) throws DaoException;

    void delete(ID id) throws DaoException;

    boolean exists(ID id) throws DaoException;

    long count() throws DaoException;

    void init() throws DaoException;

    void refresh(T t) throws DaoException;
}
