package net.ent.etrs.megamovies_falgat.view.converters;

import javafx.util.StringConverter;
import net.ent.etrs.megamovies_falgat.model.entities.Realisateur;

public class RealisateurJFXConverter extends StringConverter<Realisateur> {
    @Override
    public String toString(Realisateur realisateur) {
        return realisateur == null ? null : String.format("%s", realisateur.getNom());
    }

    @Override
    public Realisateur fromString(String s) {
        return null;
    }
}
