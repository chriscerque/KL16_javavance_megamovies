package net.ent.etrs.megamovies.model.facades;


import net.ent.etrs.megamovies.model.daos.exceptions.DaoException;
import net.ent.etrs.megamovies.model.entities.AbstractEntity;
import net.ent.etrs.megamovies.model.facades.exceptions.BusinessException;

import java.io.Serializable;
import java.util.Optional;
import java.util.Set;

public interface BaseFacade<T extends AbstractEntity, ID extends Serializable> {
    T save(T entity) throws BusinessException;

    Optional<T> find(ID id) throws BusinessException;

    Set<T> findAll() throws BusinessException;

    void delete(ID id) throws BusinessException;

    boolean exists(ID id) throws BusinessException;

    long count() throws BusinessException;
}
