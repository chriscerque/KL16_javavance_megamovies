package net.ent.etrs.tmplatejvsfx.model.dao.impl;


import net.ent.etrs.tmplatejvsfx.commons.utils.JpaUtil;
import net.ent.etrs.tmplatejvsfx.model.entities.AbstractEntity;

import java.io.Serializable;

public abstract class AbstractJpaDao<T extends AbstractEntity, ID extends Serializable> extends JpaBaseDao<T, ID> {

    protected AbstractJpaDao() {
        super.setEm(JpaUtil.getEm());
    }
}
