package net.ent.etrs.megamovie.model.entities;

import lombok.*;
import net.ent.etrs.megamovie.model.exceptions.Constante;
import net.ent.etrs.megamovie.model.exceptions.MessageErreur;
import net.ent.etrs.megamovie.model.exceptions.ValidException;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "REALISATEUR", uniqueConstraints = @UniqueConstraint(name = "REALISATEUR__NOM__UK", columnNames = {"NOM"}))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"nom"}, callSuper = false)
@ToString(of = {"nom"})
public class Realisateur extends AbstractEntity{
    
    //LB
    @Getter
    @Setter
    @NonNull 
    //BV 
    @NotBlank(message = MessageErreur.REALISATEUR_NOM_NULL)
    @Length(max = Constante.REALISATEUR_NOM_TAILLE_MAX, message = MessageErreur.REALISATEUR_NOM_TAILLE_MAX)
    //JPA
    @Column(name = "NOM", nullable = false, length = Constante.REALISATEUR_NOM_TAILLE_MAX)
    private String nom;


    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "realisateur_genre", joinColumns = @JoinColumn(name = "realisateur_id", foreignKey = @ForeignKey(name = "realisateur_id")))
    @Column(name = "GENRE")
    //TODO
    private Set<Genre> genres = new HashSet<>();



    public Set<Genre> getGenres() {
        return Collections.unmodifiableSet(this.genres);
    }

    public void ajouter(Genre genre) throws ValidException {
        if (Objects.isNull(genre)) {
            throw new ValidException(MessageErreur.REALISATEUR_GENRE_AJOUT);
        }
        this.genres.add(genre);
    }


    public void addGenre(Genre genre){
        if(genre!= null){
            this.genres.add(genre);
        }
    }


    
}
