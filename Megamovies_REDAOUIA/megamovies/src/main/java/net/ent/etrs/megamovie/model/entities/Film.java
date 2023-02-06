package net.ent.etrs.megamovie.model.entities;

import lombok.*;
import net.ent.etrs.megamovie.model.exceptions.Constante;
import net.ent.etrs.megamovie.model.exceptions.MessageErreur;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@ToString(of = {"titre", "dateSortie"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false, of = {"titre", "dateSortie"})

@Entity
@Table(name = "FILM", uniqueConstraints = @UniqueConstraint(name = "film__titre__uk", columnNames = "TITRE"))

public class Film extends AbstractEntity {

     @Getter
      @Setter
      @FutureOrPresent(message = MessageErreur.MSG_FILM_DATE_NON_VALIDE)
      @NotNull(message = MessageErreur.MSG_FILM__NULL)
      @Column(name = "DATE_SORTIE", nullable = false)
    LocalDate dateSortie;



     //LB
     @Getter @Setter
     @NonNull
     //BV
     @NotBlank(message = MessageErreur.FILM_TITRE_NULL)
     @Length(max = Constante.FILM_TITRE_TAILLE_MAX, message = MessageErreur.FILM_TITRE_TAILLE_MAX)
     //JPA
     @Column(name = "titre", nullable = false, length = Constante.FILM_TITRE_TAILLE_MAX)
     private String titre;

     @Getter @Setter
     @NotNull
     @Column(name = "genre", nullable = false, length = Constante.GENRE_TAILLE_MAX)
    private Genre genre;



     //LBK
         @Getter @Setter
         //BV
         @NotNull(message = MessageErreur.FILM_REALISATEUR_NULL_EXCEPTION)
         //JPA
         @ManyToOne
         @JoinColumn(name = "REALISATEUR_ID", nullable = false, foreignKey = @ForeignKey(name = "film__realisateur_fk"))
         private Realisateur realisateur;

}