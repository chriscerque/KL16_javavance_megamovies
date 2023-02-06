package net.ent.etrs.megamovies.model.daos.impl;


import net.ent.etrs.megamovies.model.daos.JpaUtils;
import net.ent.etrs.megamovies.model.entities.AbstractEntity;

public abstract class AbstractJPADao<T extends AbstractEntity> extends JpaBaseDao<T> {

    public AbstractJPADao() {
        this.setEm(JpaUtils.getEm());
    }
}
