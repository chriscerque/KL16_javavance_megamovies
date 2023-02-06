package net.ent.etrs.megamovies.model.daos.commons;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JpaUtil {
    private static EntityManager em;

    static {
        JpaUtil.em = Persistence.createEntityManagerFactory("megamovies").createEntityManager(); // pu name
    }

    public static EntityManager getEm() {
        return JpaUtil.em;
    }
}
