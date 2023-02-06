package net.ent.etrs.templatefx.model.entities;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.templatefx.commons.validator.ValidException;
import net.ent.etrs.templatefx.commons.validator.ValidatorUtils;
import net.ent.etrs.templatefx.model.entities.references.Genre;

import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EntitiesFactory {

    public static Film fabriquerFilm(final String titre, final LocalDate dateSortie, final Genre genre, final Realisateur realisateur) throws ValidException {
        Film f = new Film();
        f.setTitre(titre);
        f.setDateSortie(dateSortie);
        f.setRealisateur(realisateur);
        f.setGenre(genre);
        return ValidatorUtils.validate(f);
    }

    public static Realisateur fabriquerRealisateur(final String nom, final Set<Genre> genres) throws ValidException {
        Realisateur r = new Realisateur();
        r.setNom(nom);
        for (Genre g : genres) {
            r.ajouterGenre(g);
        }
        return ValidatorUtils.validate(r);
    }
}
