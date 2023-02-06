package net.ent.etrs.template.model.entities;

import lombok.*;
import net.ent.etrs.template.model.entities.references.Constante;
import net.ent.etrs.template.model.entities.references.Genre;
import net.ent.etrs.template.model.entities.references.MessageErreur;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;


@Entity
@Table(name = "FILM", uniqueConstraints = @UniqueConstraint(name = "FILM__TITRE__UK", columnNames = {"TITRE"}))

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)

@EqualsAndHashCode(callSuper = false, of = {"titre"})
@ToString(callSuper = true, of = {"ID","titre","dateSortie"})
public class Film extends AbstractEntity{

    //LB
    @Getter
    @Setter
    @NonNull
    //BV
    @NotBlank(message = MessageErreur.TITRE__NULL)
    @Length(max = Constante.TITRE__TAILLE_MAX, message = MessageErreur.TITRE__TAILLE_MAX)
    //JPA
    @Column(name = "TITRE", nullable = false, length = Constante.TITRE__TAILLE_MAX)
    private String titre;


    //LB
    @Getter
    @Setter
    @NonNull
    //BV
    @NotBlank(message = MessageErreur.DATESORTIE__NULL)
    //JPA
    @Column(name = "DATE_SORTIE", nullable = false)
    private LocalDate dateSortie;

    // LB
    @Getter
    @Setter
    @NonNull
    //BV
    @NotBlank(message = MessageErreur.GENRE__NULL)
    //JPA
    @Enumerated(EnumType.STRING)
    @Column(name = "GENRE", nullable = false, length = 50)
    private Genre genre;


    //LB
    @Getter
    @Setter
    @NonNull
    //BV
    @NotBlank(message = MessageErreur.REALISATEUR_NOM_NULL)
    //JPA
    @ManyToOne
    @JoinColumn(name = "REALISATEUR_ID", nullable = false, foreignKey = @ForeignKey(name = "FILM__REALISATEUR_FK"))
    private Realisateur realisateur;


}
