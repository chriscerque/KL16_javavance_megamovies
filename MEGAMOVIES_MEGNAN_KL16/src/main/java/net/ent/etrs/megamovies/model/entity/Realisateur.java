package net.ent.etrs.megamovies.model.entity;

import lombok.*;
import net.ent.etrs.megamovies.exception.RealisateurException;
import net.ent.etrs.megamovies.model.entity.references.ConstantesMetier;
import net.ent.etrs.megamovies.model.entity.references.Genre;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

//LB
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"nom"}, callSuper = false)
@ToString(of = {"nom"}, callSuper = true)
// JPA
@Entity
@Table(name = "REALISATEUR", uniqueConstraints = @UniqueConstraint(name = "UK__REALISATEUR__NOM", columnNames = {"NOM"}))

public class Realisateur extends AbstractEntity {

    //LB
    @Getter
    @Setter
    //BV
    @NotEmpty(message = ConstantesMetier.REALISATEUR_NOM_NULL)
    @Size(min = ConstantesMetier.REALISATEUR_NOM_TAILLE_MIN, max = ConstantesMetier.REALISATEUR_NOM_TAILLE_MAX, message = ConstantesMetier.REALISATEUR_NOM_TAILLE)
    //JPA
    @Column(name = "NOM", nullable = false, length = ConstantesMetier.REALISATEUR_NOM_TAILLE_MAX)
    private String nom;

    //JPA
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "REALISATEUR_GENRE", joinColumns = @JoinColumn(name = "REALISATEUR_ID", foreignKey = @ForeignKey(name = "FK__REALISATEUR_GENRE__REALISATEUR_ID")))
    @Column(name = "GENRE", nullable = false)
    private Set<Genre> genres = new HashSet<>();

    public Set<Genre> getGenres() {
        return Collections.unmodifiableSet(genres);
    }

    public void ajouterGenre(Genre genre) throws RealisateurException {
        if (Objects.isNull(genre)) {
            throw new RealisateurException(ConstantesMetier.AJOUTER_GENRE_NULL);
        }
        this.genres.add(genre);
    }
}
