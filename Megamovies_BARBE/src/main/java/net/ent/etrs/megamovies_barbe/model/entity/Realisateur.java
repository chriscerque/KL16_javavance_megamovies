package net.ent.etrs.megamovies_barbe.model.entity;


import lombok.*;
import net.ent.etrs.megamovies_barbe.commons.validator.ValidException;
import net.ent.etrs.megamovies_barbe.model.entity.references.Constantes;
import net.ent.etrs.megamovies_barbe.model.entity.references.ConstantesErreur;
import net.ent.etrs.megamovies_barbe.model.entity.references.Genre;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

// Java Persistence API
@Entity
@Table(name = "REALISATEUR", uniqueConstraints = @UniqueConstraint(name = "REALISATEUR__NOM__UK",
        columnNames = {"nom"}))
// Lombok
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false, of = {"nom"})
@ToString(callSuper = true, of = {"nom", "genres"})
public class Realisateur extends AbstractEntity {

    // Lombok
    @NonNull @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC)
    // Bean Validation
    @NotNull(message = ConstantesErreur.NOT_NULL)
    @Size(min = Constantes.TAILLE_MIN_NOM, max = Constantes.TAILLE_MAX_NOM, message = ConstantesErreur.TAILLE_NON_RESPECTEE)
    @NotBlank
    // Java Persistence API
    @Column(name = "nom", nullable = false, length = 255)
    private String nom;

    // Lombok
    @NonNull
    // Bean Validation
    @NotNull(message = ConstantesErreur.NOT_NULL)
    // Java Persistence API
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "REALISATEUR_GENRE", joinColumns = @JoinColumn(name = "REALISATEUR_ID", foreignKey = @ForeignKey(name = "realisateur_genre_pkey")))
    @Column(name = "GENRE", nullable = false, length = 50)
    private Set<Genre> genres = new HashSet<>();


    public void ajouterGenre(final Genre genres) throws ValidException {
        if (genres == null) {
            throw new ValidException(ConstantesErreur.NOT_NULL);
        }
        this.genres.add(genres);
    }


    public void supprimerGenre(final Genre genres) throws ValidException {
        if (genres == null) {
            throw new ValidException(ConstantesErreur.NOT_NULL);
        }
        this.genres.remove(genres);
    }


    public Set<Genre> getGenres() {
        return Collections.unmodifiableSet(this.genres);
    }
}
