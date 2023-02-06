package net.ent.etrs.template.controllers.converter;

import javafx.util.StringConverter;
import net.ent.etrs.template.model.entities.Realisateur;

public class ConverterRealisateur extends StringConverter<Realisateur> {

    @Override
    public String toString(Realisateur realisateur) {
        return realisateur == null ? "realisateur" : realisateur.getNom();
//        return realisateur.getNom();
    }

    @Override
    public Realisateur fromString(String s) {
        return null;
    }
}
