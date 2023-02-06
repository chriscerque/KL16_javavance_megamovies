package net.ent.etrs.megamovies.model.daos.impl;

import net.ent.etrs.megamovies.model.daos.base.JpaBaseDao;
import net.ent.etrs.megamovies.model.daos.commons.JpaUtils;


public class AbstractDao<T, K> extends JpaBaseDao<T, K> {
	
	public AbstractDao() {
		this.setEm(JpaUtils.getEm());
	}
	
}
