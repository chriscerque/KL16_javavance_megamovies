package net.ent.etrs.megamovies_barbe.model.dao;


import net.ent.etrs.megamovies_barbe.commons.utils.JpaUtils;
import net.ent.etrs.megamovies_barbe.model.dao.base.JpaBaseDao;
import net.ent.etrs.megamovies_barbe.model.entity.AbstractEntity;

public abstract class AbstractJpaDao<T extends AbstractEntity> extends JpaBaseDao<T> {

    public AbstractJpaDao() {
        this.setEm(JpaUtils.getEm());
    }
}
