package net.ent.etrs.megamovies.model.daos.impl;


import net.ent.etrs.megamovies.model.daos.BaseDao;
import net.ent.etrs.megamovies.model.daos.exceptions.DaoException;
import net.ent.etrs.megamovies.model.daos.exceptions.references.ConstantesDaoException;
import net.ent.etrs.megamovies.model.entities.AbstractEntity;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.*;

public class MemSetDao<T extends AbstractEntity> implements BaseDao<T, Serializable> {
    private static Long idCount = 1L;
    private final Set<T> persistance = new HashSet<>();
    private Field idField;

    protected MemSetDao() {
        this.idField = AbstractEntity.class.getDeclaredFields()[0];
        this.idField.setAccessible(true);
    }

    private void paramNullChecker(final Object o) throws DaoException {
        if (Objects.isNull(o)) {
            throw new DaoException(ConstantesDaoException.DAO_OBJECT_NULL);
        }
    }

    @Override
    public Optional<T> save(final T t) throws DaoException {
        this.paramNullChecker(t);
        if (t.getId() == null) {
            this.persistance.add(t);
            try {
                idField.set(t, idCount);
            } catch (IllegalAccessException e) {
                throw new DaoException(e.getMessage(), e);
            }
            idCount++;
        } else {
            this.persistance.remove(t);
            this.persistance.add(t);
        }
        return this.find(t.getId());
    }

    @Override
    public Optional<T> find(final Serializable id) throws DaoException {
        this.paramNullChecker(id);
        for (T t : persistance) {
            if (t.getId().equals(id)) {
                return Optional.of(t);
            }
        }
        return Optional.empty();
    }

    @Override
    public Iterable<T> findAll() {
        return Collections.unmodifiableSet(this.persistance);
    }

    @Override
    public void delete(final Serializable id) throws DaoException {
        Optional<T> t = this.find(id);
        t.ifPresent(persistance::remove);
    }

    @Override
    public boolean exists(final Serializable id) throws DaoException {
        this.paramNullChecker(id);
        return !Objects.isNull(this.find(id));
    }

    @Override
    public long count() throws DaoException {
        return this.persistance.size();
    }

    @Override
    public void init() throws DaoException {

    }

    @Override
    public void refresh(final T t) throws DaoException {

    }
}
