package net.ent.etrs.template.model.dao.impl;


import lombok.*;
import net.ent.etrs.template.model.dao.commons.JpaBaseDao;
import net.ent.etrs.template.model.dao.commons.JpaUtils;

import javax.persistence.UniqueConstraint;

public class AbstractDao<T, K> extends JpaBaseDao<T, K> {

    public AbstractDao() {
        this.setEm(JpaUtils.getEm());
    }



}
