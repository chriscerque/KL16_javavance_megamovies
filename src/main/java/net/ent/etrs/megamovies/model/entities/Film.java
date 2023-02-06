package net.ent.etrs.megamovies.model.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import net.ent.etrs.megamovies.model.references.Genre;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "FILM", uniqueConstraints = @UniqueConstraint(name = "FILM__TITRE__UK", columnNames = "TITRE"))

@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@NoArgsConstructor
public class Film extends AbstractEntity {
    @Getter
    @Setter
    @Column(name = "TITRE", nullable = false, length = 150)
    String titre;
    @Getter
    @Setter
    @Column(name = "DATE_SORTIE", nullable = false)
    LocalDate dateSortie;
    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    @Column(name = "GENRE", nullable = false)
    Genre genre;
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "REALISATEUR_ID", nullable = false, foreignKey = @ForeignKey(name = "FILM__REALISATEUR_FK"))
    Realisateur realisateur;

    public Film(String titre, LocalDate dateSortie) {
        super();
        this.titre = titre;
        this.dateSortie = dateSortie;
    }

}
