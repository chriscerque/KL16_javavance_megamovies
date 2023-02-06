package net.ent.etrs.megamovie.model.daos.jpa;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
/**
 * Classe de constantes contenant les messages d'affichage de l'application.
 * @author christophe.cerqueira
 *
 */
public class REQ {
    public static final String CLIENT_BY_COURRIEL = "SELECT c FROM Client c WHERE c.courriel = :courriel";
    public static final String ALL_OPERATIONS_TO_COMPTE = "SELECT o FROM Compte c JOIN c.operations o where c.id = :compte";
    public static final String ALL_COMPTE_TO_CLIENT = "SELECT co FROM Client c LEFT JOIN c.comptes co WHERE c= :client";
}
