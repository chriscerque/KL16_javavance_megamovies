package net.ent.etrs.megamovies_falgat.model.entities;

import lombok.*;
import net.ent.etrs.megamovies_falgat.commons.validator.ValidException;
import net.ent.etrs.megamovies_falgat.model.entities.references.Genre;
import net.ent.etrs.megamovies_falgat.model.references.ConstantesModel;

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
@ToString(callSuper = true, of = {"nom", "genres"})
public class Realisateur extends AbstractEntity{

    //JPA
    @Column(name = "NOM", nullable = false, length = ConstantesModel.REALISATEUR_NOM_LENGTH)
    //BV
    @NotBlank(message = ConstantesModel.REALISATEUR_NOM_NULL)
    @Size(min = ConstantesModel.REALISATEUR_NOM_LENGTH_MIN, max = ConstantesModel.REALISATEUR_NOM_LENGTH, message = ConstantesModel.REALISATEUR_NOM_LENGTH_ERROR)
    //LBK
    @Getter @Setter
    private String nom;


    //JPA
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "REALISATEUR_GENRE", joinColumns = @JoinColumn(name = "REALISATEUR_ID", foreignKey = @ForeignKey(name = "REALISATEUR_ID")))
    @Column(name = "GENRE", length = ConstantesModel.REALISATEUR_GENRE_LENGTH)
    private Set<Genre> genres = new HashSet<>();


    public Set<Genre> getGenres(){
        return Collections.unmodifiableSet(this.genres);
    }

    public void ajouterGenres(final Set<Genre> g) throws ValidException {
        if(Objects.isNull(g)){
            throw new ValidException(ConstantesModel.ADD_GENRE_ERROR);
        }
        this.genres.addAll(g);
    }


}
