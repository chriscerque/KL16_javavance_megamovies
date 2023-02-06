package net.ent.etrs.megamovies.model.entity.references;

public class ConstantesMetier {

    //PATTERN
    public final static String PATTERN_FORMAT_DATE = "dd/MM/yyyy";


    //CONSTANTES RÉALISATEUR

    //CONSTANTES STRING
    public static final String AJOUTER_GENRE_NULL = "Le genre à ajouter est null";
    public static final String REALISATEUR_NOM_NULL = "Le nom du réalisateur est null";
    public static final String REALISATEUR_NOM_TAILLE = "Le nombre de caractere du nom doit se situer entre 3 et 50";

    //CONSTANTES INT
    public static final int REALISATEUR_NOM_TAILLE_MAX = 50;
    public static final int REALISATEUR_NOM_TAILLE_MIN = 3;


    //CONSTANTES FILM

    //CONSTANTES STRING
    public static final String FILM_DATE_SORTIE_NULL = "La date de sortie du film doit être renseignée";
    public static final String FILM_DATE_SORTIE_PASSE = "La date de sortie du film doit être dans le passé ou le jour même";
    public static final String FILM_TITRE_NULL = "Le titre du film doit être renseigné";
    public static final String FILM_TITRE_TAILLE = "La taille du nom du film doit se situer entre 3 et 50 caractères";
    public static final String FILM_GENRE_NULL = "Le genre du film doit être renseigné";
    public static final String FILM_REALISATEUR_NULL = "Le réalisateur du film doit être renseigné";

    //CONSTANTES INT
    public static final int FILM_TITRE_TAILLE_MIN = 3;
    public static final int FILM_TITRE_TAILLE_MAX = 50;
}
