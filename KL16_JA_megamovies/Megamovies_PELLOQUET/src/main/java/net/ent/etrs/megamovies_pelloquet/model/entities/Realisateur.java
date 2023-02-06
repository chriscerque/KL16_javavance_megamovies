package net.ent.etrs.megamovies_pelloquet.model.entities;

import lombok.Getter;
import lombok.Setter;
import net.ent.etrs.megamovies_pelloquet.model.entities.references.ConstantesEntities;
import net.ent.etrs.megamovies_pelloquet.model.entities.references.Genre;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.Set;

@Entity
@Table(name = "REALISATEUR", uniqueConstraints = @UniqueConstraint(name = "REALISATEUR__NOM_UK", columnNames = "NOM"))
public class Realisateur extends AbstractEntity{

    //LOMBOK
    @Getter
    @Setter
    //BV
    @NotNull(message = ConstantesEntities.MESSAGE_NOM_NULL)
    @Size(min = 2, max = 50, message = ConstantesEntities.MESSAGE_TAILLE_NOM)
    //JPA
    @Column(name = "NOM", nullable = false, length = 50)
    private String nom;

    @Enumerated(EnumType.STRING)
    @ElementCollection
    @CollectionTable(name = "REALISATEUR_GENRE", joinColumns = @JoinColumn(name = "REALISATEUR_ID", foreignKey = @ForeignKey(name = "REALISATEUR_GENRE_PKEY")))
    @Column(name = "GENRE")
    private Set<Genre> genres;

    public void ajouterGenres(Genre g) {
        genres.add(g);
    }

    public void retirerGenres(Genre g) {
        genres.remove(g);
    }


    public Set<Genre> getGenres() {
        return Collections.unmodifiableSet(genres);
    }
}
