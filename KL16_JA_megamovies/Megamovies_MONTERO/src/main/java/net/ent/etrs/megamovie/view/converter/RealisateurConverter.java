package net.ent.etrs.megamovie.view.converter;

import javafx.util.StringConverter;
import net.ent.etrs.megamovie.model.entities.Realisateur;

public class RealisateurConverter extends StringConverter<Realisateur> {
    @Override
    public String toString(final Realisateur realisateur) {
        return realisateur == null ? null : String.format("%s", realisateur.getNom().toLowerCase());
    }

    @Override
    public Realisateur fromString(final String s) {
        return null;
    }
}
