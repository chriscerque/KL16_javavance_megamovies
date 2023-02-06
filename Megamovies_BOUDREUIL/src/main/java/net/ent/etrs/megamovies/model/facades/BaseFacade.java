package net.ent.etrs.megamovies.model.facades;

import net.ent.etrs.megamovies.model.entities.AbstractEntity;
import net.ent.etrs.megamovies.model.facades.exceptions.BusinessException;

import java.io.Serializable;
import java.util.Optional;
import java.util.Set;

public interface BaseFacade<T extends AbstractEntity, ID extends Serializable> {

    Optional<T> save(T t) throws BusinessException;

    Optional<T> select(ID id) throws BusinessException;

    Set<T> selectAll() throws BusinessException;

    void delete(ID id) throws BusinessException;

    void init() throws BusinessException;
}
