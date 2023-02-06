package net.ent.etrs.megamovies.model.entities;


import lombok.*;
import net.ent.etrs.megamovies.model.entities.references.Constantes;
import net.ent.etrs.megamovies.model.entities.references.Genre;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "FILM", uniqueConstraints = @UniqueConstraint(name = "FILM__TITRE_UK", columnNames = {"TITRE"}))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false, of = {"titre"})
@ToString(callSuper = true, of = {"titre", "genre", "dateSortie", "realisateur"})
public class Film extends AbstractEntity {

    @Getter
    @Setter
    @NonNull
    @NotNull(message = Constantes.FILM_GENRE_NULL)
    @Enumerated(EnumType.STRING)
    @Column(name = "GENRE", nullable = false)
    public Genre genre;

    @Getter
    @Setter
    @NonNull
    @NotBlank(message = Constantes.FILM_TITRE_NULL)
    @Size(max = Constantes.FILM_TITRE_TAILLE_MAX_VALEUR, message = Constantes.FILM_TITRE_TAILLE_MESSAGE)
    @Column(name = "TITRE", nullable = false, length = Constantes.FILM_TITRE_TAILLE_MAX_VALEUR)
    public String titre;

    @Getter
    @Setter
    @NonNull
    @NotNull(message = Constantes.FILM_DATE_SORTIE_NULL)
    @Column(name = "DATE_SORTIE", nullable = false)
    private LocalDate dateSortie;


    @Getter
    @Setter
    @NonNull
    @NotNull(message = Constantes.FILM_REALISATEUR_NULL)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "REALISATEUR_ID", nullable = false, foreignKey = @ForeignKey(name = "FILM__REALISATEUR_FK"))
    private Realisateur realisateur;

    public Genre connaitreGenre() {
        return this.genre;
    }
}
