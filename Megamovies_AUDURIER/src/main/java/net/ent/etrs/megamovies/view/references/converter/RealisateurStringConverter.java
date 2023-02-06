package net.ent.etrs.megamovies.view.references.converter;


import javafx.util.StringConverter;
import net.ent.etrs.megamovies.model.entities.Realisateur;

public class RealisateurStringConverter extends StringConverter<Realisateur> {

    @Override
    public String toString(final Realisateur realisateur) {
        return realisateur == null ? null : realisateur.getNom();
    }

    @Override
    public Realisateur fromString(final String s) {
        return null;
    }
}
