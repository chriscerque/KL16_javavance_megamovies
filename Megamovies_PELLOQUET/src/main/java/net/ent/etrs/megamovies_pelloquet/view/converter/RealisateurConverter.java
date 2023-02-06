package net.ent.etrs.megamovies_pelloquet.view.converter;

import javafx.util.StringConverter;
import net.ent.etrs.megamovies_pelloquet.model.entities.Realisateur;

public class RealisateurConverter extends StringConverter<Realisateur> {
    @Override
    public String toString(Realisateur realisateur) {
        return realisateur == null ? null : String.format("%s", realisateur.getNom());
    }

    @Override
    public Realisateur fromString(String string) {
        return null;
    }
}
