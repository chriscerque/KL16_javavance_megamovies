package net.ent.etrs.megamovies.model.daos;

import net.ent.etrs.megamovies.model.daos.exceptions.DaoException;
import net.ent.etrs.megamovies.model.entities.AbstractEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface BaseDao<T extends AbstractEntity, ID extends Serializable> {

    Optional<T> find(ID id) throws DaoException;

    List<T> findAll() throws DaoException;

    Optional<T> save(T entity) throws DaoException;

    void delete(ID id) throws DaoException;

    boolean exists(ID id) throws DaoException;

    long count() throws DaoException;

    void init() throws DaoException;

    void refresh(T t) throws DaoException;
}
