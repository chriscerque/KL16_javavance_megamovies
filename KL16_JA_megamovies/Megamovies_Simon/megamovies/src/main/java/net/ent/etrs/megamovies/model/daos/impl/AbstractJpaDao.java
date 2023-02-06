package net.ent.etrs.megamovies.model.daos.impl;


import net.ent.etrs.megamovies.model.daos.commons.JpaUtils;
import net.ent.etrs.megamovies.model.entities.AbstractEntity;

public abstract class AbstractJpaDao<T extends AbstractEntity> extends JpaBaseDao<T> {

    public AbstractJpaDao() {
        this.setEm(JpaUtils.getEm());
    }
}
