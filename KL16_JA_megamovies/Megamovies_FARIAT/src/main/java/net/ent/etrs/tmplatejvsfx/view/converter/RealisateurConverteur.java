package net.ent.etrs.tmplatejvsfx.view.converter;

import javafx.util.StringConverter;
import net.ent.etrs.tmplatejvsfx.model.entities.Realisateur;

public class RealisateurConverteur extends StringConverter<Realisateur> {
    @Override
    public String toString(final Realisateur realisateur) {
        return realisateur.getNom();
    }

    @Override
    public Realisateur fromString(final String s) {
        return null;
    }
}
