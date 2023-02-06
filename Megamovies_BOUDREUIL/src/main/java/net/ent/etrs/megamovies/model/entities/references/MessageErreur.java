package net.ent.etrs.megamovies.model.entities.references;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.validation.ConstraintViolation;
import java.util.Set;

/**
 * Classe de constantes contenant les messages d'affichage de l'application.
 *  @author joan.boudreuil
 *
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageErreur {

    public static final String FILM_TITRE_NULL = "le titre du film ne peut pas être null";
    public static final String FILM_TITRE_TAILLE_MAX = "le titre dud film ne peut comporter plus de " + Constante.FILM_TITRE_TAILLE_MAX + " caractères";
    public static final String REALISATEUR_NOM_TAILLE_MAX = "Le nom doit contenir au minimum 4 caractères.";
    public static final String REALISATEUR_NOM_NULL = "le nom du réal ne peut pas être null";

    public static final String REALISATEUR_GENRE_SUPPR = "Echec de la suppr d'un genre d'un réal" ;
    public static final String REALISATEUR_GENRE_AJOUT = "Echec de l'ajout d'un genre d'un réal" ;
    public static final String FILM_DATE_SORTIE_NULL = "la date de sortie du film ne peut pas être null";
    public static final String FILM_GENRE_NULL = "le genre du film ne peut pas être null";
    public static final String FILM_GENRE_TAILLE_MAX = "le genre du film ne peut comporter plus de " + Constante.FILM_GENRE_TAILLE_MAX + "caractères";
    public static final String FILM_REALISATEUR_NULL = "le réal du film ne peut pas être null";
}
