package net.ent.etrs.megamovies.model.entity;

import lombok.*;
import net.ent.etrs.megamovies.model.entity.references.ConstantesMetier;
import net.ent.etrs.megamovies.model.entity.references.Genre;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;

//LB
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"titre"}, callSuper = false)
@ToString(of = {"date_sortie", " titre", " genre", "realisateur_id"}, callSuper = true)
// JPA
@Entity
@Table(name = "FILM", uniqueConstraints = @UniqueConstraint(name = "UK__FILM__TITRE", columnNames = {"TITRE"}))

public class Film extends AbstractEntity {

    //LB
    @Getter
    @Setter
    //BV
    @NotEmpty(message = ConstantesMetier.FILM_DATE_SORTIE_NULL)
    @PastOrPresent(message = ConstantesMetier.FILM_DATE_SORTIE_PASSE)
    //JPA
    @Column(name = "DATE_SORTIE", nullable = false)
    private LocalDate dateSortie;

    //LB
    @Getter
    @Setter
    //BV
    @NotEmpty(message = ConstantesMetier.FILM_TITRE_NULL)
    @Size(min = ConstantesMetier.FILM_TITRE_TAILLE_MIN, max = ConstantesMetier.FILM_TITRE_TAILLE_MAX, message = ConstantesMetier.FILM_TITRE_TAILLE)
    //JPA
    @Column(name = "TITRE", nullable = false, length = ConstantesMetier.FILM_TITRE_TAILLE_MAX)
    private String titre;

    //LB
    @Getter
    @Setter
    //BV
    @NotNull(message = ConstantesMetier.FILM_GENRE_NULL)
    //JPA
    @Enumerated(EnumType.STRING)
    @Column(name = "GENRE", nullable = false)
    private Genre genre;

    //LB
    @Getter
    @Setter
    //BV
    @NotNull(message = ConstantesMetier.FILM_REALISATEUR_NULL)
    //JPA
    @JoinColumn(name = "REALISATEUR_ID", nullable = false, foreignKey = @ForeignKey(name = "FILM__REALISATEUR_FK"))
    @OneToOne
    private Realisateur realisateur;
}
