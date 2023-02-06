package net.ent.etrs.templatefx.model.entities;

import lombok.*;
import net.ent.etrs.templatefx.commons.validator.ValidException;
import net.ent.etrs.templatefx.model.entities.references.ConstantesModel;
import net.ent.etrs.templatefx.model.entities.references.Genre;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "FILM", uniqueConstraints = @UniqueConstraint(name = "FILM__TITRE__UK", columnNames = "TITRE"))
@EqualsAndHashCode(callSuper = false, of = {"titre"})
@ToString(callSuper = true, of = {"titre", "dateSortie", "genre", "realisateur"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Film extends AbstractEntity implements Serializable {

    //LBK
    @Getter
    @Setter
    //BV
    @NotBlank(message = ConstantesModel.FILM_TITRE_NULL)
    @Size(max = ConstantesModel.FILM_TITRE_TAILLE, message = ConstantesModel.FILM_TITRE_TAILLE_DEPASSE)
    //JPA
    @Column(name = "TITRE", nullable = false, length = ConstantesModel.FILM_TITRE_TAILLE)
    private String titre;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = ConstantesModel.FILM_DATE_SORTIE_NULL)
    //Pas de @PastOrPresent car un film peut être annoncé
    //JPA
    @Column(name = "DATE_SORTIE", nullable = false)
    private LocalDate dateSortie;

    //LBK
    @Getter
    //BV
    @NotNull(message = ConstantesModel.FILM_GENRE_NULL)
    //JPA
    @Enumerated(EnumType.STRING)
    @Column(name = "GENRE", length = ConstantesModel.FILM_GENRE_TAILLE, nullable = false)
    private Genre genre;

    //LBK
    @Getter
    @Setter
    //BV
    @NotNull(message = ConstantesModel.FILM_REALISATEUR_NULL)
    //JPA
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REALISATEUR_ID", nullable = false, foreignKey = @ForeignKey(name = "FILM__REALISATEUR_FK"))
    private Realisateur realisateur;

    public void setGenre(final Genre genre) throws ValidException {
        if (realisateur.getGenres().contains(genre)) {
            this.genre = genre;
        } else {
            throw new ValidException(ConstantesModel.FILM_GENRE_NON_COMPATIBLE_REALISATEUR);
        }
    }
}
