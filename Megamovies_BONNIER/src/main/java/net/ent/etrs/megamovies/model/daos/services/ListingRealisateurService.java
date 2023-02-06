package net.ent.etrs.megamovies.model.daos.services;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import net.ent.etrs.megamovies.model.entities.Realisateur;
import net.ent.etrs.megamovies.model.facades.FacadeMetierRealisateur;
import net.ent.etrs.megamovies.model.facades.impl.FacadeFactory;

public class ListingRealisateurService extends Service<ObservableList<Realisateur>> {


    private ObservableList<Realisateur> oLstRealisateur = FXCollections.observableArrayList();

    private FacadeMetierRealisateur leMetier = FacadeFactory.fabriquerFacadeMetierRealisateur();

    @Override
    protected Task<ObservableList<Realisateur>> createTask() {
        return new Task<>() {

            @Override
            protected ObservableList<Realisateur> call() throws Exception {
                for (Realisateur m : leMetier.readAll()) {
                    oLstRealisateur.add(m);
                }
                return oLstRealisateur;
            }

        };
    }
}
