package net.ent.etrs.templatefx.model.entities.references;

public class ConstantesModel {
    public static final int REALISATEUR_NOM_TAILLE = 255;
    public static final String REALISATEUR_NOM_TAILLE_DEPASSE = "Le nom du réalisateur ne doit pas dépasser " + REALISATEUR_NOM_TAILLE + " carctères";
    public static final String REALISATEUR_NOM_NULL = "Le nom du réalisateur doit être renseigné";
    public static final String REALISATEUR_GENRE_NULL = "Le genre doit être renseigné";
    public static final int FILM_TITRE_TAILLE = 150;
    public static final String FILM_TITRE_TAILLE_DEPASSE = "Le titre du film ne doit pas dépasser " + FILM_TITRE_TAILLE + " carctères";
    public static final String FILM_TITRE_NULL = "Le titre du film doit être renseigné";
    public static final String FILM_DATE_SORTIE_NULL = "La date de sortie du film doit être renseigné";
    public static final int FILM_GENRE_TAILLE = 50;
    public static final String FILM_GENRE_NULL = "Le genre du film doit être renseigné";
    public static final String FILM_REALISATEUR_NULL = "Le réalisateur du film doit être renseigné";
    public static final String FILM_GENRE_NON_COMPATIBLE_REALISATEUR = "Le genre du film doit être un des genre du réalisateur";
    public static final String RECHERCHE_ALL_BDD_FILM = "Recherche de tous les films de la BDD";
    public static final String RECHERCHE_BDD_FILM_BY_REALISATEUR = "Recherche de tous les film de %s";
    public static final String SUPPRESION_BDD_FILM = "Suppresion du film %s";
    public static final String SAUVEGARDE_BDD_FILM = "Sauvegarde du film %s";
    public static final String RECHERCHE_ALL_BDD_REALISATEUR = "Recherche de tous les réalisateur de la BDD";
    public static final String SAUVEGARDE_BDD_REALISATEUR = "Sauvegarde du réalisateur %s";
    public static final String SUPPRESION_BDD_REALISATEUR = "Suppresion du réalisateur %s";
    public static final String RECHERCHE_BDD_REALISATEUR_BY_GENRE = "Recherche de tous les réalisateur possèdant le genre %s";
}
