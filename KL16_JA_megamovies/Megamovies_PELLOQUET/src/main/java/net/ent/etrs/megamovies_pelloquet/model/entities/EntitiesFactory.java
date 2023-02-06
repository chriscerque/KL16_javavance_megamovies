package net.ent.etrs.megamovies_pelloquet.model.entities;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.megamovies_pelloquet.commons.validator.ValidException;
import net.ent.etrs.megamovies_pelloquet.commons.validator.ValidatorUtils;
import net.ent.etrs.megamovies_pelloquet.model.entities.references.Genre;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EntitiesFactory {

//    public static Commande fabriquerCommande(Client client, List<Produit> produits) throws ProduitException, ValidException {
//        Commande commande = new Commande();
//        commande.setDateCommande(LocalDateTime.now());
//
//        commande.setClient(client);
//        for (Produit abstractProduit : produits) {
//
//            commande.ajouterProduits(abstractProduit);
//
//        }
//        return ValidatorUtils.validate(commande);
//    }

    public static Realisateur fabriquerRealisateur(String nom) throws ValidException {
        Realisateur realisateur = new Realisateur();
        realisateur.setNom(nom);
        return ValidatorUtils.validate(realisateur);
    }

    public static Film fabriquerFilm(String titre, LocalDate dateSortie, Genre genre, Realisateur realisateur) throws ValidException {
        Film film = new Film();
        film.setGenre(genre);
        film.setRealisateur(realisateur);
        film.setTitre(titre);
        film.setDateSortie(dateSortie);
        return ValidatorUtils.validate(film);
    }


}
