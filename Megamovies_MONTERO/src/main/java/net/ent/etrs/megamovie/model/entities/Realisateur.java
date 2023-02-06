package net.ent.etrs.megamovie.model.entities;


import lombok.*;
import net.ent.etrs.megamovie.commons.validator.ValidException;
import net.ent.etrs.megamovie.model.entities.references.Constantes;
import net.ent.etrs.megamovie.model.entities.references.Genre;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "REALISATEUR", uniqueConstraints = @UniqueConstraint(name = "REALISATEUR__NOM__UK", columnNames = {"NOM"}))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"nom"}, callSuper = false)
@ToString(of = {"nom"}, callSuper = true)
public class Realisateur extends AbstractEntity {

    // LB
    @Getter
    @Setter
    // BV
    @NotBlank(message = Constantes.REALISATEUR_NOM_NULL)
    @Size(max = Constantes.REALISATEUR_NOM_TAILLE_MAX_VALEUR, message = Constantes.REALISATEUR_NOM_TAILLE_MESSAGE)
    // JPA
    @Column(name = "NOM", nullable = false, length = Constantes.REALISATEUR_NOM_TAILLE_MAX_VALEUR)
    public String nom;


    //JPA
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "REALISATEUR_GENRE", joinColumns = @JoinColumn(name = "REALISATEUR_ID", foreignKey = @ForeignKey(name = "REALISATEUR_ID")))
    @Column(name = "GENRE")
    private Set<Genre> genres = new HashSet<>();

    public Set<Genre> getGenres() {
        return Collections.unmodifiableSet(genres);
    }

    public void ajouterGenre(Genre genre) throws ValidException {
        if (Objects.isNull(genre)) {
            throw new ValidException(Constantes.REALISATEUR_GENRE_AJOUT);
        }
        this.genres.add(genre);
    }

    public void supprimerGenre(Genre genre) throws ValidException {
        if (Objects.isNull(genre)) {
            throw new ValidException(Constantes.REALISATEUR_GENRE_SUPPR);
        }
        if (!this.genres.contains(genre)) {
            throw new ValidException(Constantes.REALISATEUR_GENRE_NONPRESENT);
        }
        this.genres.remove(genre);
    }
}
