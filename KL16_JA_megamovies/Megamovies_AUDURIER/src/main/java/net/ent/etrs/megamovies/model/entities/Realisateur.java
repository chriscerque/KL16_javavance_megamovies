package net.ent.etrs.megamovies.model.entities;


import lombok.*;
import net.ent.etrs.commons.validator.ValidException;
import net.ent.etrs.megamovies.model.entities.references.ConstantesEntities;
import net.ent.etrs.megamovies.model.entities.references.Genre;
import net.ent.etrs.megamovies.model.exception.references.ConstantesMetierMsg;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "REALISATEUR", uniqueConstraints = @UniqueConstraint(name = "REALISATEUR__NOM__UK", columnNames = {"NOM"}))
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {""}, callSuper = false)
@ToString(of = {"id", "nom", "genres"})
public class Realisateur extends AbstractEntity {
    //LB
    @Getter
    @Setter
    @NonNull
    //BV 
    @NotBlank(message = ConstantesMetierMsg.REALISATEUR_NOM_NULL)
    @Length(max = ConstantesEntities.REALISATEUR_NOM_TAILLE_MAX, message = ConstantesMetierMsg.REALISATEUR_NOM_TAILLE_MAX)
    //JPA
    @Column(name = "NOM", nullable = false, length = ConstantesEntities.REALISATEUR_NOM_TAILLE_MAX)
    private String nom;


    //JPA
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "REALISATEUR_GENRE", joinColumns = @JoinColumn(name = "REALISATEUR_ID", foreignKey = @ForeignKey(name = "REALISATEUR_ID")))
    @Column(name = "GENRE")
    private Set<Genre> genres = new HashSet<>();


// gerer genres

    public Set<Genre> getgenres() {
        return Collections.unmodifiableSet(this.genres);
    }

    public void ajouterGenre(Genre genre) throws ValidException {
        if (Objects.isNull(genre)) {
            throw new ValidException(ConstantesMetierMsg.REALISATEUR_GENRE_AJOUT);
        }
        this.genres.add(genre);
    }

    public void supprimerGenre(Genre genre) throws ValidException {
        if (Objects.isNull(genre)) {
            throw new ValidException(ConstantesMetierMsg.REALISATEUR_GENRE_SUPPR);
        }
        this.genres.remove(genre);
    }
}
