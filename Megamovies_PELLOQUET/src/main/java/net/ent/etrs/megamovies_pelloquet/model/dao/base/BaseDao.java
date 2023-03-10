package net.ent.etrs.megamovies_pelloquet.model.dao.base;


import net.ent.etrs.megamovies_pelloquet.model.dao.DaoException;

import java.io.Serializable;
import java.util.Optional;

public interface BaseDao<T, ID extends Serializable> {

    Optional<T> find(ID id) throws DaoException;

    Iterable<T> findAll() throws DaoException, DaoException;

    Optional<T> save(T entity) throws DaoException;

    void delete(ID id) throws DaoException;

    boolean exists(ID id) throws DaoException;

    long count() throws DaoException;
}
