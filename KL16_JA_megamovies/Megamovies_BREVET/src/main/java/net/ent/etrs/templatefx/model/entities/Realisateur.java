package net.ent.etrs.templatefx.model.entities;

import lombok.*;
import net.ent.etrs.templatefx.commons.validator.ValidException;
import net.ent.etrs.templatefx.model.entities.references.ConstantesModel;
import net.ent.etrs.templatefx.model.entities.references.Genre;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "REALISATEUR", uniqueConstraints = @UniqueConstraint(name = "REALISATEUR__NOM__UK", columnNames = "NOM"))
@EqualsAndHashCode(callSuper = false, of = {"nom"})
@ToString(callSuper = true, of = {"nom"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Realisateur extends AbstractEntity implements Serializable {

    //LBK
    @Getter
    @Setter
    //BV
    @NotBlank(message = ConstantesModel.REALISATEUR_NOM_NULL)
    @Size(max = ConstantesModel.REALISATEUR_NOM_TAILLE, message = ConstantesModel.REALISATEUR_NOM_TAILLE_DEPASSE)
    //JPA
    @Column(name = "NOM", nullable = false, length = ConstantesModel.REALISATEUR_NOM_TAILLE)
    private String nom;

    //JPA
    @ElementCollection
    @JoinTable(name = "REALISATEUR_GENRE", joinColumns = @JoinColumn(name = "REALISATEUR_ID"))
    @Enumerated(EnumType.STRING)
    @Column(name = "GENRE")
    private Set<Genre> genres = new HashSet<>();

    public void ajouterGenre(final Genre genre) throws ValidException {
        if (genre == null) {
            throw new ValidException(ConstantesModel.REALISATEUR_GENRE_NULL);
        }
        this.genres.add(genre);
    }

    public void supprimerGenre(final Genre genre) throws ValidException {
        if (genre == null) {
            throw new ValidException(ConstantesModel.REALISATEUR_GENRE_NULL);
        }
        this.genres.remove(genre);
    }

    public Set<Genre> getGenres() {
        return Collections.unmodifiableSet(this.genres);
    }
}
