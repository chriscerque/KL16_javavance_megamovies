package net.ent.etrs.megamovies.model.dao.jpa;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ReqDao {


    public static final String LISTER_TRIE_PAR_PUISSANCE = "select v from Voiture v order by v.puissance desc";
    public static final String RECHERCHER_FILM_PAR_TITRE = "SELECT f FROM Film f WHERE f.titre =:titre";

    public static final String LISTER_PAR_MODELE = " select v from Voiture v where v.modele like '%:mod%' ";
    public static final String LISTER_PAR_MARQUE = " select v from Voiture v where v.marque= :m";
    public static final String COMPTER_PAR_MARQUE = "select count(v.immatriculation) from Voiture v where v.marque= :m";
    public static final String RECHERCHER_MARQUE_PAR_LIBELLE = "select m from Marque m where m.libelle = :libelle";
}
