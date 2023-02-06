package net.ent.etrs.megamovies.model.facades.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.ent.etrs.megamovies.model.daos.BaseDao;
import net.ent.etrs.megamovies.model.daos.exceptions.DaoException;
import net.ent.etrs.megamovies.model.entities.AbstractEntity;
import net.ent.etrs.megamovies.model.facades.BaseFacade;
import net.ent.etrs.megamovies.model.facades.exceptions.BusinessException;
import org.apache.commons.collections4.IterableUtils;

import java.io.Serializable;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public abstract class AbstractBaseFacade<T extends AbstractEntity> implements BaseFacade<T, Serializable> {
    @NonNull
    BaseDao<T, Serializable> dao;


    @Override
    public Optional<T> save(T t) throws BusinessException {
        try {
            return this.dao.save(t);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<T> select(Serializable id) throws BusinessException {
        try {
            return this.dao.find(id);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public Set<T> selectAll() throws BusinessException {
        try {
            return IterableUtils.toList(dao.findAll()).stream().collect(Collectors.toSet());
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(Serializable id) throws BusinessException {
        try {
            this.dao.delete(id);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public void init() throws BusinessException {
        try {
            this.dao.init();
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    public BaseDao<T, Serializable> getDao() {
        return this.dao;
    }
}
