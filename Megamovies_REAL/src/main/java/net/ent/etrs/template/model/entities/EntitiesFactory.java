package net.ent.etrs.template.model.entities;

import lombok.*;
import net.ent.etrs.template.commons.utils.validator.ValidException;
import net.ent.etrs.template.commons.utils.validator.ValidatorUtils;
import net.ent.etrs.template.model.entities.references.Constante;
import net.ent.etrs.template.model.entities.references.Genre;
import net.ent.etrs.template.model.entities.references.MessageErreur;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor(access= AccessLevel.PRIVATE)
public final class EntitiesFactory {
    
    public static Film fabriquerFilm(final String titre, final Genre genre, final LocalDate dateSortie, final Realisateur realisateur) throws ValidException {
        Film f = new Film();
        f.setTitre(titre);
        f.setGenre(genre);
        f.setDateSortie(dateSortie);
        f.setRealisateur(realisateur);
        return ValidatorUtils.validate(new Film());
    }

    public static Realisateur fabriquerRealisateur(final String nom) throws ValidException {
        Realisateur r = new Realisateur();
        r.setNom(nom);
        return ValidatorUtils.validate(new Realisateur());
    }

}
