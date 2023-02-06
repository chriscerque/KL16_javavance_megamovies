package net.ent.etrs.megamovies.model.entities;

import lombok.*;
import net.ent.etrs.megamovies.model.entities.references.ConstanteMetier;
import net.ent.etrs.megamovies.model.entities.references.Genre;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @NoArgsConstructor : génère le constructeur sans argument et public.
 * @AllArgsConstructor
 * @EqualsAndHashCode : génère equals et hashCode (et d'autres méthodes) sur les champs donnés.
 * @ToString : génère toString sur les champs donnés.
 * @NonNull : indique au Builder tous les champs obligatoires.
 * @Getter : génère tous les getters sur les champs.
 * @Setter: génère tous les setters sur les champs.
 * @NotNull : la référence ne peut pas être nulle.
 * @Length Valeur Max Min pour une Variable.
 */

//LB
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false, of = {"titre"})
@ToString(callSuper = true, of = {"dateSortie","genre","titre","realisateur"})
//JPA
@Entity
@Table(name = "FILM", uniqueConstraints = @UniqueConstraint(name = "UK__FILM__TITRE", columnNames = {"TITRE"}))
public class Film extends AbstractEntity {

    //LB
    @NonNull
    @Getter
    @Setter
    //BV
    @NotNull(message = ConstanteMetier.DATE_SORTIE_EXCEPTION)
    //JPA
    @Column(name = "DATE_SORTIE", nullable = false)
    private LocalDate dateSortie;

    //LB
    @NonNull
    @Getter
    @Setter
    //BV
    @NotNull(message = ConstanteMetier.FILM_GENRE_EXCEPTION)
    //JPA
    @Enumerated(EnumType.STRING)
    @Column(name = "Genre", nullable = false)
    private Genre genre;

    //LB
    @Getter
    @Setter
    @NonNull
    //BV
    @NotBlank(message = ConstanteMetier.TITRE_FILM_NULL)
    @Length(max = ConstanteMetier.TITRE_FILM_TAILLE_MAX, message = ConstanteMetier.TITRE_FILM_TAILLE_MAX_EXCEPTION)
    //JPA
    @Column(name = "TITRE", nullable = false, length = ConstanteMetier.TITRE_FILM_TAILLE_MAX)
    private String titre;

    //LB
    @Getter
    @Setter
    //BV
    @NotNull(message = ConstanteMetier.REALISATEUR_FILM_NULL)
    //JPA
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REALISATEUR_ID", nullable = false, foreignKey = @ForeignKey(name = "FK__FILM_REALISATEUR__REALISATEUR_ID"))
    private Realisateur realisateur;

}
