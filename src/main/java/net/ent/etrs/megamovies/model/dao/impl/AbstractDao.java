package net.ent.etrs.megamovies.model.dao.impl;


import net.ent.etrs.megamovies.model.dao.base.JpaBaseDao;
import net.ent.etrs.megamovies.model.dao.commons.JpaUtils;

public class AbstractDao<T, K> extends JpaBaseDao<T, K> {

    public AbstractDao() {
        this.setEm(JpaUtils.getEm());
    }

}
