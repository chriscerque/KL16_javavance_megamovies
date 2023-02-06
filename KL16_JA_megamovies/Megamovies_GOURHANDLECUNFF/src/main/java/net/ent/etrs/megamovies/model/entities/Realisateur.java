package net.ent.etrs.megamovies.model.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import net.ent.etrs.megamovies.model.entities.references.Constante;
import net.ent.etrs.megamovies.model.entities.references.Genre;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString(of = {"nom"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false, of = {"nom"})

@Entity
@Table(name = "REALISATEUR", uniqueConstraints = @UniqueConstraint(name = "REALISATEUR__NOM__UK", columnNames = "NOM"))
public class Realisateur extends AbstractEntity {
    @Getter
    @Setter
    @NotEmpty(message = Constante.MSG_NOM_NON_VIDE)
    @Length(min = Constante.REALISATEUR_NOM_TAILLE_MIN, max = Constante.REALISATEUR_NOM_TAILLE_MAX, message = Constante.MSG_NOM_TAILLE)
    @Column(name = "NOM", length = Constante.REALISATEUR_NOM_TAILLE_MAX, nullable = false)
    String nom;


    //JPA
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "REALISATEUR_GENRE", joinColumns = @JoinColumn(name = "REALISATEUR_ID", foreignKey = @ForeignKey(name = "FK__REALISATEUR_GENRE__REALISATEUR_ID")))
    @Column(name = "GENRE")
    Set<Genre> genres = new HashSet<>();

    public Set<Genre> getGenres() {
        return Collections.unmodifiableSet(this.genres);
    }

    public void ajouterGenre(final Genre genre) {
        if (genre != null) {
            this.genres.add(genre);
        }
    }
}
