package net.ent.etrs.ultramp3_GOUIN.model.daos.impl;


import net.ent.etrs.ultramp3_GOUIN.commons.utils.JpaUtil;
import net.ent.etrs.ultramp3_GOUIN.model.entities.AbstractEntity;

import java.io.Serializable;

public abstract class AbstractJpaDao<T extends AbstractEntity, ID extends Serializable> extends JpaBaseDao<T, ID> {
    
    protected AbstractJpaDao() {
        super.setEm(JpaUtil.getEm());
    }
}
