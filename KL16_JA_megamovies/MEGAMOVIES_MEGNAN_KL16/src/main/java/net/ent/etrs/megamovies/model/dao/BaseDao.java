package net.ent.etrs.megamovies.model.dao;

import net.ent.etrs.megamovies.exception.DaoException;
import net.ent.etrs.megamovies.model.entity.AbstractEntity;

import java.io.Serializable;
import java.util.Optional;

public interface BaseDao<T extends AbstractEntity, ID extends Serializable> {

    T save(T entity) throws DaoException;

    Optional<T> find(ID id) throws DaoException;

    Iterable<T> findAll() throws DaoException;

    void delete(ID id) throws DaoException;

    boolean exists(ID id) throws DaoException;

    long count() throws DaoException;

}
