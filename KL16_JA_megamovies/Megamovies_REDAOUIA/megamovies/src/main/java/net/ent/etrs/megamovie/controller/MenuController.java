package net.ent.etrs.megamovie.controller;

import javafx.scene.control.MenuItem;
import net.ent.etrs.megamovie.view.Screens;
import net.ent.etrs.megamovie.view.utils.FxmlUtils;

public class MenuController extends AbstractController{
    public MenuItem menuLister;
    public MenuItem menuAjouter;

    public void lister() {
        FxmlUtils.chargerScene(Screens.Liste, new ListeController());
    }

    public void ajouter() {
        FxmlUtils.chargerScene(Screens.Ajout, new AjoutController());
    }
}
