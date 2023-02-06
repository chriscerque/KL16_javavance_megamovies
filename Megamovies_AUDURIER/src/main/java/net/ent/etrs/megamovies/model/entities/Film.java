package net.ent.etrs.megamovies.model.entities;


import lombok.*;
import net.ent.etrs.commons.validator.ValidException;
import net.ent.etrs.megamovies.model.entities.references.ConstantesEntities;
import net.ent.etrs.megamovies.model.entities.references.Genre;
import net.ent.etrs.megamovies.model.exception.references.ConstantesMetierMsg;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Entity
@Table(name = "FILM", uniqueConstraints = @UniqueConstraint(name = "FILM__TITRE__UK", columnNames = {"TITRE"}))
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(of = {""}, callSuper = false)
@ToString(of = {"id", "titre", "genre", "dateSortie", "realisateur"})
public class Film extends AbstractEntity {
    //LB
    @Getter
    @Setter
    @NonNull
//BV
    @NotBlank(message = ConstantesMetierMsg.FILM_TITRE_NULL)
    @Length(max = ConstantesEntities.FILM_TITRE_TAILLE_MAX, message = ConstantesMetierMsg.FILM_TITRE_TAILLE_MAX)
//JPA
    @Column(name = "TITRE", nullable = false, length = ConstantesEntities.FILM_TITRE_TAILLE_MAX)
    private String titre;

    //LB
    @Getter
    @Setter
    @NonNull
//BV
    @NotNull(message = ConstantesMetierMsg.FILM_GENRE_NULL)
//JPA
    @Enumerated(EnumType.STRING)
    @Column(name = "GENRE", nullable = false)
    private Genre genre;

    //LB
    @Getter
    @Setter
    @NonNull
//BV
    @NotNull(message = ConstantesMetierMsg.FILM_DATE_SORTIE_NULL)
    @PastOrPresent(message = ConstantesMetierMsg.FILM_DATE_SORTIE_FUTURE)
//JPA
    @Column(name = "DATE_SORTIE", nullable = false)
    private LocalDate dateSortie;

    //LB
    @Getter
    @Setter
    @NonNull
//BV
    @NotNull(message = ConstantesMetierMsg.FILM_REALISATEUR_NULL)
//JPA
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "REALISATEUR_ID", nullable = false, foreignKey = @ForeignKey(name = "FILM__REALISATEUR_FK"))
    private Realisateur realisateur;

    protected Film(@NonNull final String titre, @NonNull final Genre genre, @NonNull final LocalDate dateSortie, @NonNull final Realisateur realisateur)
            throws ValidException {
        this.titre = titre;
        this.genre = genre;
        this.dateSortie = dateSortie;
        try {
            this.setRealisateur(realisateur);
        } catch (ValidException e) {
            throw new ValidException(e.getMessage());
        }
    }

    public void setRealisateur(final Realisateur realisateur) throws ValidException {
        if (realisateur == null) {
            throw new ValidException(ConstantesMetierMsg.FILM_REALISATEUR_NULL);
        }
        if (this.checkGenreRealisateur(realisateur)) {
            this.realisateur = realisateur;
        } else {
            throw new ValidException(ConstantesMetierMsg.FILM_REALISATEUR_GENRE_IMPOSSIBLE);
        }

    }

    private boolean checkGenreRealisateur(final Realisateur realisateur) {
        return realisateur.getgenres().contains(this.genre);
    }
}
