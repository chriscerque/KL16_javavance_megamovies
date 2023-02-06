package net.ent.etrs.megamovies.model.facades;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.ent.etrs.megamovies.model.daos.BaseDao;
import net.ent.etrs.megamovies.model.daos.exceptions.DaoException;
import net.ent.etrs.megamovies.model.entities.AbstractEntity;
import net.ent.etrs.megamovies.model.exception.BusinessException;

import java.io.Serializable;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class AbstractBaseFM<T extends AbstractEntity> implements IBaseFacadeMetier<T, Serializable> {
    private static boolean init = false;
    @NonNull
    private BaseDao<T, Serializable> dao;

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
    public Iterable<T> selectAll() throws BusinessException {
        try {
            return this.dao.findAll();
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(final Serializable id) throws BusinessException {
        try {
            this.dao.delete(id);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public boolean exists(final Serializable id) throws BusinessException {
        try {
            return this.dao.exists(id);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public long count() throws BusinessException {
        try {
            return this.dao.count();
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public void refresh(final T t) throws BusinessException {
        try {
            this.dao.refresh(t);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public void init() throws BusinessException {
        try {
            if (!init) {
                dao.init();
                init = true;
            }
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    public BaseDao<T, Serializable> getDao() {
        return this.dao;
    }
}
