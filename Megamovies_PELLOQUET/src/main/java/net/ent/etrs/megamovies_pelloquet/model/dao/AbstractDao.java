package net.ent.etrs.megamovies_pelloquet.model.dao;


import net.ent.etrs.megamovies_pelloquet.model.dao.base.JpaBaseDao;
import net.ent.etrs.megamovies_pelloquet.model.dao.commons.JpaUtils;

public class AbstractDao<T, K> extends JpaBaseDao<T, K> {

    public AbstractDao() {
        this.setEm(JpaUtils.getEm());
    }

}
