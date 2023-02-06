package net.ent.etrs.megamovie.model.daos.jpa.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * Classe utilitaire capable de donner l'instance d'EntityManager en échange
 * du nom de l'unité de persistence ( cf. votre persistence.xml)
 *
 * @author christophe.cerqueira
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JpaUtils {

    private static EntityManager em;

    public synchronized static EntityManager getEm(String unitName) {
        if (em == null) {
            em = Persistence.createEntityManagerFactory(unitName).createEntityManager();
        }
        return em;
    }
}
