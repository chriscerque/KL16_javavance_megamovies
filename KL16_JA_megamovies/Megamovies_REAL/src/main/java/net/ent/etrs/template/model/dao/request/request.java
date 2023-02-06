package net.ent.etrs.template.model.dao.request;

public final class request {
    public static final String FIND_BY_REALISATEUR = "select f from Film f where f.realisateur = :realisateur";
    public static final String FIND_BY_GENRE = "select r from Realisateur f where f.genre = :genre";

}
