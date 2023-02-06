package net.ent.etrs.megamovies.model.dao.impl;

import net.ent.etrs.megamovies.commons.utils.console.JpaUtil;
import net.ent.etrs.megamovies.model.entity.AbstractEntity;

import java.io.Serializable;

public abstract class AbstractJpaDao<T extends AbstractEntity, ID extends Serializable> extends JpaBaseDao<T, ID> {

    protected AbstractJpaDao() {
        super.setEm(JpaUtil.getEm());
    }
}
