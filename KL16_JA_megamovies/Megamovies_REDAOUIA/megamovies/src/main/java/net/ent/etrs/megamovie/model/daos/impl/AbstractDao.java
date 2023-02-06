package net.ent.etrs.megamovie.model.daos.impl;


import net.ent.etrs.megamovie.model.daos.base.JpaBaseDao;
import net.ent.etrs.megamovie.model.daos.jpa.utils.JpaUtils;

public class AbstractDao<T, K> extends JpaBaseDao<T, K> {

    public AbstractDao() {
        this.setEm(JpaUtils.getEm("pu"));
    }

}
