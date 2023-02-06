package net.ent.etrs.megamovie.model.facades;

import net.ent.etrs.megamovie.model.entities.AbstractEntity;
import net.ent.etrs.megamovie.model.facades.exceptions.BusinessException;

import java.io.Serializable;
import java.util.Optional;

/**
 * Façade métier proposant les opérations utiles
 * à l'application.
 */

public interface IBaseFacadeMetier<T extends AbstractEntity, ID extends Serializable> {

    Optional<T> save(T t) throws BusinessException;

    Optional<T> select(ID id) throws BusinessException;

    Iterable<T> selectAll() throws BusinessException;

    void delete(ID id) throws BusinessException;

    boolean exists(ID id) throws BusinessException;

    long count() throws BusinessException;

    void refresh(T t) throws BusinessException;

    void init() throws BusinessException;
}
