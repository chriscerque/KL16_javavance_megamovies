package net.ent.etrs.megamovies_falgat.model.references;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConstantesModel {


    public static final String FILM_DATE_NULL = "La date de sortie du film dne doit pas être null.";
    public static final int FILM_GENRE_LENGTH = 50;
    public static final String FILM_GENRE_NULL = "Le genre d'u film doit être renseigné.";
    public static final int FILM_TITRE_LENGTH = 150;
    public static final String FILM_TITRE_NULL = "Le titre du film doit être renseigné";
    public static final int FILM_TITRE_LENGTH_MIN = 1;
    public static final String FILM_TITRE_LENGTH_ERROR = "Le titre du film doit faire entre 1 et 150 caractères.";
    public static final String FILM_REALISATEUR_NULL = "Le réalisateur du film doit être renseigné";
    public static final int REALISATEUR_GENRE_LENGTH = 50;
    public static final int REALISATEUR_NOM_LENGTH = 255;
    public static final String REALISATEUR_NOM_NULL = "Le nom du réalisateur doit être renseigné";
    public static final int REALISATEUR_NOM_LENGTH_MIN = 1;
    public static final String REALISATEUR_NOM_LENGTH_ERROR = "Le nom du réalisateur doit faire entre 1 et 255 caractères";
    public static final String ADD_GENRE_ERROR = "Le genre à ajouter est incorrect;";
    public static final String SET_REALISATEUR_ERROR = "Erreur, le réalisateur ne peut pas réaliser ce film";
}
