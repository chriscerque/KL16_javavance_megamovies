package net.ent.etrs.megamovies.model.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import net.ent.etrs.megamovies.model.entities.references.Constante;
import net.ent.etrs.megamovies.model.entities.references.Genre;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@SuppressWarnings("serial")
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString(of = {"titre", "genre", "dateSortie"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false, of = {"titre"})

@Entity
@Table(name = "FILM", uniqueConstraints = @UniqueConstraint(name = "FILM__TITRE__UK", columnNames = "TITRE"))
public class Film extends AbstractEntity {
    @Getter
    @Setter
    @NotEmpty(message = Constante.MSG_NOM_NON_VIDE)
    @Length(min = Constante.FILM_NOM_TAILLE_MIN, max = Constante.FILM_NOM_TAILLE_MAX, message = Constante.MSG_NOM_TAILLE)
    @Column(name = "TITRE", length = Constante.FILM_NOM_TAILLE_MAX, nullable = false)
    String titre;

    //LB
    @Getter @Setter
    @NonNull
    //BV
    @NotNull(message = Constante.FILM_DATE_NULL)
    @PastOrPresent(message = Constante.FILM_DATE_FUTUR)
    //JPA
    @Column(name = "DATE_SORTIE", nullable = false)
    private LocalDate dateSortie;

    //LB
    @Getter @Setter
    @NonNull
    //BV
    @NotNull(message = Constante.FILM_REALISATEUR_NULL)
    //JPA
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "REALISATEUR_ID", nullable = false, foreignKey = @ForeignKey(name = "FK__FILM__REALISATEUR_ID"))
    private Realisateur realisateur;

    //LB
    @Getter @Setter
    @NonNull
    //BV
    @NotNull(message = Constante.FILM_GENRE_NULL)
    //JPA
    @Enumerated(EnumType.STRING)
    @Column(name = "GENRE", length = Constante.FILM_GENRE_TAILLE_MAX, nullable = false)
    private Genre genre;
}
