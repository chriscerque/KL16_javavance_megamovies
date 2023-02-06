package net.ent.etrs.templatefx.view.converter;

import javafx.util.StringConverter;
import net.ent.etrs.templatefx.model.entities.Realisateur;

public class RealisateurConverter extends StringConverter<Realisateur> {
    @Override
    public String toString(final Realisateur realisateur) {
        return realisateur.getNom();
    }

    @Override
    public Realisateur fromString(final String s) {
        return null;
    }
}
