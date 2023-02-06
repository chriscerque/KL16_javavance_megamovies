package net.ent.etrs.tmplatejvsfx.model.entities;

import lombok.*;
import net.ent.etrs.tmplatejvsfx.model.entities.reference.ConstanteMetier;
import net.ent.etrs.tmplatejvsfx.model.entities.reference.Genre;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.time.LocalDate;



@ToString(callSuper = true, of = {"dateSortie", "genre", "titre", "realisateur"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false, of = {"titre"})

@Entity
@Table(name = "FILM", uniqueConstraints = @UniqueConstraint(name = "FILM__TITRE__UK", columnNames = "TITRE"))

public class Film extends AbstractEntity implements Serializable {


 //LB
 @Getter
 @Setter

 //BV
 @NotNull(message = ConstanteMetier.FILM_DATE_SORTIE_NULL)
 @PastOrPresent(message = ConstanteMetier.FILM_DATE_SORTIE_FUTURE)
 //JPA
 @Column(name = "DATE_SORTIE", nullable = false)
 private LocalDate dateSortie;

 //LB
 @Getter
 @Setter

 //BV
 @NotNull(message = ConstanteMetier.FILM_GENRE_NULL)
 //JPA
 @Enumerated(EnumType.STRING)
 @Column(name = "GENRE", nullable = false)
 private Genre genre;

 //LB
 @Getter
 @Setter

 //BV
 @NotBlank(message = ConstanteMetier.FILM_NOM_NULL)
 @Length(max = ConstanteMetier.FILM_NOM_TAILLE_MAX, min = ConstanteMetier.FILM_NOM_TAILLE_MIN, message = ConstanteMetier.FILM_NOM_TAILLE)
 //JPA
 @Column(name = "TITRE", nullable = false, length = ConstanteMetier.FILM_NOM_TAILLE_MAX)
 private String titre;

 //LB
 @Getter @Setter
 //BV
 @NotNull(message = ConstanteMetier.FILM_REALISATEUR_NULL)
 //JPA
 @ManyToOne(fetch = FetchType.LAZY)
 @JoinColumn(name = "REALISATEUR_ID", nullable = false, foreignKey = @ForeignKey(name = "FK__FILM__REALISATEUR_ID"))
 private Realisateur realisateur;
}
