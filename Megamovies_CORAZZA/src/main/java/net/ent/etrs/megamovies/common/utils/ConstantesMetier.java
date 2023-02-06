package net.ent.etrs.megamovies.common.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
/**
 * Classe de constantes contenant les messages d'affichage de l'application.
 * @author christophe.cerqueira
 *
 */
public class ConstantesMetier {

    public final static String PATTERN_FORMAT_DATE = "dd/MM/yyyy";

    public static final String MSG_NOM_NON_VIDE = "Le nom doit être renseigné.";
    public static final int REALISATEUR_NOM_TAILLE_MIN = 3;

    public static final int REALISATEUR_NOM_TAILLE_MAX = 50;
    public static final String MSG_NOM_TAILLE = "Le nom doit contenir entre 3 et 50 caractères.";

    public static final String MSG_DATE_NULL = "La date doit être renseignée.";
    public static final String MSG_GENRE_NULL = "Le genre doit être renseigné.";
}
