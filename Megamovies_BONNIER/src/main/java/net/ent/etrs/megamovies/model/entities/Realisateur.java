package net.ent.etrs.megamovies.model.entities;


import lombok.*;
import net.ent.etrs.megamovies.commons.validator.ValidException;
import net.ent.etrs.megamovies.model.entities.references.Constantes;
import net.ent.etrs.megamovies.model.entities.references.Genre;

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
@EqualsAndHashCode(callSuper = false, of = {"nom"})
@ToString(callSuper = true, of = {"nom", "genre"})
public class Realisateur extends AbstractEntity {

    @Getter
    @Setter
    @NotBlank(message = Constantes.REALISATEUR_NOM_NULL)
    @Size(max = Constantes.REALISATEUR_NOM_TAILLE_MAX_VALEUR, message = Constantes.REALISATEUR_NOM_TAILLE_MESSAGE)
    @Column(name = "NOM", nullable = false, length = Constantes.REALISATEUR_NOM_TAILLE_MAX_VALEUR)
    public String nom;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "REALISATEUR_GENRE", joinColumns = @JoinColumn(name = "REALISATEUR_ID", foreignKey = @ForeignKey(name = "FK__REALISATEUR_GENRE__REALISATEUR_ID")))
    @JoinColumn(name = "GENRE_ID", foreignKey = @ForeignKey(name = "FK__GENRE__REALISATEUR_ID"))
    private Set<Genre> genre = new HashSet<>();

    public Set<Genre> getGenres() {
        return Collections.unmodifiableSet(genre);
    }

    public void ajouterGenre(Genre genre) throws ValidException {
        if (Objects.isNull(genre)) {
            throw new ValidException(Constantes.REALISATEUR_GENRE_AJOUT);
        }
        this.genre.add(genre);
    }

    public void supprimerGenre(Genre genre) throws ValidException {
        if (Objects.isNull(genre)) {
            throw new ValidException(Constantes.REALISATEUR_GENRE_SUPPR);
        }
        this.genre.remove(genre);
    }


}
