package net.ent.etrs.megamovies.model.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.ent.etrs.megamovies.common.utils.ConstantesMetier;
import net.ent.etrs.megamovies.model.entities.references.Genre;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.Objects;


@Table(name = "FILM")
@ToString(of = {"id", "dateSortie, genre,titre"})
public class Film extends AbstractEntity {


    @Getter
    @Setter
    @NotNull(message = ConstantesMetier.MSG_DATE_NULL)
    @Column(name = "DATE_SORTIE")
    private LocalDate dateSortie;

    @Getter
    @Setter
    @NotNull(message = ConstantesMetier.MSG_GENRE_NULL)
    @Column(name = "GENRE")
    private Genre genre;

    @Getter
    @Setter
    @NotNull(message = ConstantesMetier.MSG_GENRE_NULL)
    @Column(name = "titre")
    private String titre;

    @Getter
    @Setter
    @NotNull(message = ConstantesMetier.MSG_GENRE_NULL)
    @JoinColumn(name = "REALISATEUR_ID", nullable = false, foreignKey = @ForeignKey(name = "film__realisateur_fk"))
    @Column(name = "realisateur_id")
    private Realisateur realisateur;








    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Film film = (Film) o;
        return Objects.equals(dateSortie, film.dateSortie) && genre == film.genre && Objects.equals(titre, film.titre) && Objects.equals(realisateur, film.realisateur);
    }
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), dateSortie, genre, titre, realisateur);
    }
}
