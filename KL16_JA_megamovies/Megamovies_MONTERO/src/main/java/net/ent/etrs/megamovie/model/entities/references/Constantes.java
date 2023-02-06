package net.ent.etrs.megamovie.model.entities.references;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constantes {
    public static final String TITRE_APROPOS = "A propos de la page";
    public static final String HEADER_APROPOS = "Créer par :";
    public static final String MSG_APROPOS = "MONTERO";
    public static final String REALISATEUR_NOM_NULL = "Le nom doit etre renseigne.";
    public static final int REALISATEUR_NOM_TAILLE_MAX_VALEUR = 25;
    public static final String REALISATEUR_NOM_TAILLE_MESSAGE = "Le nom est trop grand (plus de 25 lettres).";
    public static final String REALISATEUR_GENRE_AJOUT = "Le genre est null.";
    public static final String REALISATEUR_GENRE_SUPPR = "Le genre est null.";
    public static final String FILM_TITRE_NULL = "Le titre doit etre renseigne.";
    public static final int FILM_TITRE_TAILLE_MAX_VALEUR = 50;
    public static final String FILM_TITRE_TAILLE_MESSAGE = "Le titre du film est trop long. (moins de 50 lettres)";
    public static final String FILM_GENRE_NULL = "Le genre est null.";
    public static final String FILM_DATE_SORTIE_NULL = "la date de sortie du film est null.";
    public static final String FILM_REALISATEUR_NULL = "le realisateur du film est null.";
    public static final String REALISATEUR_GENRE_NONPRESENT = "Le genre n'est pas present.";
    public static final String LOG_FILENAME = "LoggerFile";
    public static final String FILM_IDVIDEO_NULL = "le film doit contenir une bande annonce";
}
