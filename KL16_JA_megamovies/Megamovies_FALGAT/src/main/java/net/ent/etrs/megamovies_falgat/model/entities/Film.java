package net.ent.etrs.megamovies_falgat.model.entities;

import lombok.*;
import net.bytebuddy.pool.TypePool;
import net.ent.etrs.megamovies_falgat.commons.validator.ValidException;
import net.ent.etrs.megamovies_falgat.model.entities.references.Genre;
import net.ent.etrs.megamovies_falgat.model.references.ConstantesModel;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "FILM", uniqueConstraints = @UniqueConstraint(name = "FILM__TITRE__UK", columnNames = {"TITRE"}))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false, of = {"titre"})
@ToString(callSuper = true, of = {"titre", "dateSortie", "genre"})
public class Film extends AbstractEntity{

    //JPA
    @Column(name = "DATE_SORTIE", nullable = false)
    //BV
    @NotNull(message = ConstantesModel.FILM_DATE_NULL)
    //Pas de contrainte de passé, de présent ou de futur car un film peut avoir une date de
    //Sortie n'importe quand
    //LBK
    @Getter @Setter
    private LocalDate dateSortie;

    //JPA
    @Column(name = "GENRE", nullable = false, length = ConstantesModel.FILM_GENRE_LENGTH)
    @Enumerated(EnumType.STRING)
    //BV
    @NotNull(message = ConstantesModel.FILM_GENRE_NULL)
    //LBK
    @Getter @Setter
    private Genre genre;

    //JPA
    @Column(name = "TITRE", nullable = false, length = ConstantesModel.FILM_TITRE_LENGTH)
    //BV
    @NotBlank(message = ConstantesModel.FILM_TITRE_NULL)
    @Size(min = ConstantesModel.FILM_TITRE_LENGTH_MIN, max = ConstantesModel.FILM_TITRE_LENGTH, message = ConstantesModel.FILM_TITRE_LENGTH_ERROR)
    //LBK
    @Getter @Setter
    private String titre;

    //JPA
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REALISATEUR_ID", foreignKey = @ForeignKey(name = "FILM__REALISATEUR_FK"))
    //BV
    @NotNull(message = ConstantesModel.FILM_REALISATEUR_NULL)
    @Valid
    //LBK
    @Getter
    private Realisateur realisateur;

    public void setRealisateur(final Realisateur realisateur) throws ValidException {
        if(realisateur.getGenres().contains(this.getGenre())){
            this.realisateur = realisateur;
        } else {
            throw new ValidException(ConstantesModel.SET_REALISATEUR_ERROR);
        }
    }


}
