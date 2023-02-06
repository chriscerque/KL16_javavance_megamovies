package net.ent.etrs.megamovie.model.dao.impl;


import net.ent.etrs.megamovie.model.dao.commons.JpaUtils;
import net.ent.etrs.megamovie.model.entities.AbstractEntity;

public abstract class AbstractJPADao<T extends AbstractEntity> extends JpaBaseDao<T> {

    public AbstractJPADao() {
        this.setEm(JpaUtils.getEm());
    }
}
