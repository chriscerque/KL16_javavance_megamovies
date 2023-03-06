package net.ent.etrs.template.model.commons.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JpaUtil {
    private static EntityManager em;

    static {
        JpaUtil.em = Persistence.createEntityManagerFactory("pu-re7").createEntityManager(); // pu name
    }

    public static EntityManager getEm() {
        return JpaUtil.em;
    }
}
