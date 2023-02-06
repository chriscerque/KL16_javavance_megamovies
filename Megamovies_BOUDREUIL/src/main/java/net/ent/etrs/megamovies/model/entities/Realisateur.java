package net.ent.etrs.megamovies.model.entities;

import lombok.*;
import net.ent.etrs.megamovies.commons.validator.ValidException;
import net.ent.etrs.megamovies.model.entities.references.Constante;
import net.ent.etrs.megamovies.model.entities.references.Genre;
import net.ent.etrs.megamovies.model.entities.references.MessageErreur;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

// LB
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "nom", callSuper = true)
@ToString(of = "nom", callSuper = true)
// JPA
@Entity
@Table(name = "REALISATEUR", uniqueConstraints = @UniqueConstraint(name = "realisateur__nom__uk", columnNames = "NOM"))
public class Realisateur extends AbstractEntity {

    //LB
    @Getter @Setter
    @NonNull
    //BV
    @NotBlank(message = MessageErreur.REALISATEUR_NOM_NULL)
    @Length(max = Constante.REALISATEUR_NOM_TAILLE_MAX, message = MessageErreur.REALISATEUR_NOM_TAILLE_MAX)
    //JPA
    @Column(name = "NOM", nullable = false, length = Constante.REALISATEUR_NOM_TAILLE_MAX)
    private String nom;
    @Getter
    @Setter
    
    //JPA
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "REALISATEUR_GENRE", joinColumns = @JoinColumn(name = "REALISATEUR_ID", foreignKey = @ForeignKey(name = "FK__REALISATEUR_GENRE__REALISATEUR_ID")))
    @Column(name = "GENRE")
    private Set<Genre> genres = new HashSet<>();
    
    // gerer genres
    
    public Set<Genre> getGenres() {
        return Collections.unmodifiableSet(genres);
    }
    
    public void ajouterGenre(Genre genre) throws ValidException {
        if (Objects.isNull(genre)) {
            throw new ValidException(MessageErreur.REALISATEUR_GENRE_AJOUT);
        }
        this.genres.add(genre);
    }
    
    public void supprimerGenre(Genre genre) throws ValidException {
        if (Objects.isNull(genre)) {
            throw new ValidException(MessageErreur.REALISATEUR_GENRE_SUPPR);
        }
        this.genres.remove(genre);
    }

}
