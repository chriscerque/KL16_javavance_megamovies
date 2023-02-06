package net.ent.etrs.megamovies.model.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.ent.etrs.megamovies.common.utils.ConstantesMetier;
import net.ent.etrs.megamovies.model.entities.references.Genre;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;
import java.util.Set;


@Table(name = "REALISATEUR")
@ToString(of = {"id", "date"})
public class Realisateur extends AbstractEntity {

    @Getter
    @Setter
    @NotEmpty(message = ConstantesMetier.MSG_NOM_NON_VIDE)
    @Length(min = ConstantesMetier.REALISATEUR_NOM_TAILLE_MIN, max = ConstantesMetier.REALISATEUR_NOM_TAILLE_MAX, message = ConstantesMetier.MSG_NOM_TAILLE)
    @Column(name = "NOM", length = ConstantesMetier.REALISATEUR_NOM_TAILLE_MAX, nullable = false)
    private String nom;


    @OneToMany
    @JoinColumn(name = "REALISATEUR_GENRE", foreignKey = @ForeignKey(name = "realisateur_id"))
    private Set<Genre> genres;






    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Realisateur that = (Realisateur) o;
        return Objects.equals(nom, that.nom) && Objects.equals(genres, that.genres);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nom, genres);
    }
}
