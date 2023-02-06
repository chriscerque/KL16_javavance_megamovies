package net.ent.etrs.megamovies.model.daos.jpa;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JpaUtils {

    @Getter
    private static EntityManager em;

    static {
        //Nom de la database
        em = Persistence.createEntityManagerFactory("pu-megamovies").createEntityManager();
    }

}
