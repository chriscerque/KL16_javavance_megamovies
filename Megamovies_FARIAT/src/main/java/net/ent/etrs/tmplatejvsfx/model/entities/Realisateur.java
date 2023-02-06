package net.ent.etrs.tmplatejvsfx.model.entities;

import lombok.*;
import net.ent.etrs.tmplatejvsfx.commons.validator.ValidException;
import net.ent.etrs.tmplatejvsfx.model.entities.reference.ConstanteMetier;
import net.ent.etrs.tmplatejvsfx.model.entities.reference.Genre;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
@ToString(callSuper = true, of = {"nom"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false, of = {"nom"})

@Entity
@Table(name = "REALISATEUR", uniqueConstraints = @UniqueConstraint(name = "REALISATEUR__NOM__UK", columnNames = "NOM"))

public class Realisateur extends AbstractEntity implements Serializable {

    //LB
    @Getter
    @Setter

    //BV
    @NotBlank(message = ConstanteMetier.REALISATEUR_NOM_NULL)
    @Length(max = ConstanteMetier.REALISATEUR_NOM_TAILLE_MAX, message = ConstanteMetier.REALISATEUR_NOM_TAILLE)
    //JPA
    @Column(name = "NOM", nullable = false, length = ConstanteMetier.REALISATEUR_NOM_TAILLE_MAX)
    private String nom;
    
    
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
            throw new ValidException(ConstanteMetier.REALISATEUR_GENRE_AJOUT);
        }
        this.genres.add(genre);
    }
    
    public void supprimerGenre(Genre genre) throws ValidException {
        if (Objects.isNull(genre)) {
            throw new ValidException(ConstanteMetier.REALISATEUR_GENRE_SUPPR);
        }
        this.genres.remove(genre);
    }



}
