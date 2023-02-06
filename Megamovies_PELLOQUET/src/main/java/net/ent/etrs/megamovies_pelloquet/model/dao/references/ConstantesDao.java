package net.ent.etrs.megamovies_pelloquet.model.dao.references;

public class ConstantesDao {

    public static final String QUERY_REALISATEUR_PAR_GENRE = "SELECT r FROM Realisateur r where r.genre = :genre";
    public static final String QUERY_FILM_PAR_REALISATEUR = "SELECT f FROM Film f where f.realisateur = : realisateur";
}
