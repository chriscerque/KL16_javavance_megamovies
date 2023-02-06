package net.ent.etrs.projectname.model.dao;


import net.ent.etrs.projectname.model.dao.exception.DaoException;
import net.ent.etrs.projectname.model.entities.AbstractEntity;

import java.io.Serializable;
import java.util.Optional;

public interface BaseDao<T extends AbstractEntity, ID extends Serializable> {

    Optional<T> save(T entity) throws DaoException;

    Optional<T> find(ID id) throws DaoException;

    Iterable<T> findAll() throws DaoException;

    void delete(ID id) throws DaoException;

    boolean exists(ID id) throws DaoException;

    long count() throws DaoException;

}
