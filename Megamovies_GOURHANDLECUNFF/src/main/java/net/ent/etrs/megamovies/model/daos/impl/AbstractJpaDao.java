package net.ent.etrs.megamovies.model.daos.impl;


import net.ent.etrs.megamovies.model.daos.base.JpaBaseDao;
import net.ent.etrs.megamovies.model.daos.commons.JpaUtil;
import net.ent.etrs.megamovies.model.entities.AbstractEntity;

import java.io.Serializable;

public abstract class AbstractJpaDao<T extends AbstractEntity, ID extends Serializable> extends JpaBaseDao<T, ID> {
    
    protected AbstractJpaDao() {
        super.setEm(JpaUtil.getEm());
    }
}
