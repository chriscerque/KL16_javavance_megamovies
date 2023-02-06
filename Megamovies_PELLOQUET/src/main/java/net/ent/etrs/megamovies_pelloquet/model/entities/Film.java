package net.ent.etrs.megamovies_pelloquet.model.entities;

import lombok.Getter;
import lombok.Setter;
import net.ent.etrs.megamovies_pelloquet.model.entities.references.ConstantesEntities;
import net.ent.etrs.megamovies_pelloquet.model.entities.references.Genre;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "FILM", uniqueConstraints = @UniqueConstraint(name = "FILM__TITRE__UK", columnNames = {"TITRE"}))

public class Film extends AbstractEntity{

    //LOMBOK
    @Getter
    @Setter
    //BV
    @NotNull(message = ConstantesEntities.MESSAGE_TITRE_NULL)
    @Size(min = 1, max = 100, message = ConstantesEntities.MESSAGE_TAILLE_TITRE)
    //JPA
    @Column(name = "TITRE", nullable = false, length = 100)
    private String titre;

    //LOMBOK
    @Getter
    @Setter
    //BV
    @NotNull(message = ConstantesEntities.MESSAGE_DATE_NULL)
    @PastOrPresent
    //JPA
    @Column(name = "DATE_SORTIE", nullable = false)
    private LocalDate dateSortie;

    //LOMBOK
    @Getter
    @Setter
    //BV
    @NotNull
    //JPA
    @ManyToOne
    @JoinColumn(name = "REALISATEUR_ID", nullable = false, foreignKey = @ForeignKey(name = "FILM__ID_REALISATEUR_FK"))
    private Realisateur realisateur;

    //LOMBOK
    @Getter
    @Setter
    //BV
    @NotNull
    //JPA
    @Enumerated(EnumType.STRING)
    @Column(name = "GENRE", nullable = false, length = 20)
    private Genre genre;
}
