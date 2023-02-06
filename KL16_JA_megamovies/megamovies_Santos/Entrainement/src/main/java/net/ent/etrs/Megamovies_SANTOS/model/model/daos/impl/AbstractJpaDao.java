package net.ent.etrs.Megamovies_SANTOS.model.model.daos.impl;
import net.ent.etrs.Megamovies_SANTOS.model.model.daos.commons.JpaUtils;
import net.ent.etrs.Megamovies_SANTOS.model.model.entities.AbstractEntity;

import java.io.Serializable;

public abstract class AbstractJpaDao<T extends AbstractEntity, ID extends Serializable> extends JpaBaseDao<T, ID> {
    
    protected AbstractJpaDao() {
        super.setEm(JpaUtils.getEm());
    }
}
