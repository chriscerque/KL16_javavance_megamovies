package net.ent.etrs.megamovies.model.facades.impl;


import net.ent.etrs.megamovies.model.daos.base.BaseDao;
import net.ent.etrs.megamovies.model.daos.exceptions.DaoException;
import net.ent.etrs.megamovies.model.entities.AbstractEntity;
import net.ent.etrs.megamovies.model.facades.BaseFacade;
import net.ent.etrs.megamovies.model.facades.exceptions.BusinessException;
import org.apache.commons.collections4.IterableUtils;

import java.io.Serializable;
import java.util.Optional;
import java.util.Set;

public abstract class AbstractFacade<T extends AbstractEntity, ID extends Serializable> implements BaseFacade<T, ID> {
    BaseDao<T, ID> dao;

    @Override
    public T save(T entity) throws BusinessException {
        try {
            return this.dao.save(entity);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public Optional<T> find(ID id) throws BusinessException {
        try {
            return this.dao.find(id);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public Set<T> findAll() throws BusinessException {
        try {
            return Set.copyOf(IterableUtils.toList(this.dao.findAll()));
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public void delete(ID id) throws BusinessException {
        try {
            this.dao.delete(id);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public boolean exists(ID id) throws BusinessException {
        try {
            return this.dao.exists(id);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public long count() throws BusinessException {
        try {
            return this.dao.count();
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }
}
