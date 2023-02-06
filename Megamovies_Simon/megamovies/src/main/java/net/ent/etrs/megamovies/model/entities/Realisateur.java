package net.ent.etrs.megamovies.model.entities;


import lombok.*;
import net.ent.etrs.megamovies.model.entities.references.ConstanteMetier;
import net.ent.etrs.megamovies.model.entities.references.Genre;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false, of = {"nom"})
@ToString(callSuper = true, of = {"nom"})
//JPA
@Entity
@Table(name = "REALISATEUR", uniqueConstraints = @UniqueConstraint(name = "UK__REALISATEUR__NOM", columnNames = {"NOM"}))
public class Realisateur extends AbstractEntity{
    //LB
    @Getter @Setter
    @NonNull
    //BV
    @NotBlank(message = ConstanteMetier.NOM_REALISATEUR_NULL)
    @Length(max = ConstanteMetier.NOM_REALISATEUR_TAILLE_MAX, message = ConstanteMetier.NOM_REALISATEUR_TAILLE_MAX_EXCEPTION)
    //JPA
    @Column(name = "NOM", nullable = false, length = ConstanteMetier.NOM_REALISATEUR_TAILLE_MAX)
    private String nom;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "REALISATEUR_GENRE", joinColumns = @JoinColumn(name = "GENRE_ID", foreignKey = @ForeignKey(name = "FK__REALISATEUR_GENRE__REALISATEUR_ID")))
    @Column(name = "GENRE")
    private Set<Genre> genres = new HashSet<>() ;

    public void ajouterGenres(Genre genre) throws Exception {
        if (Objects.isNull(genre)) {
            throw new Exception(ConstanteMetier.MSG_GENRE_AJOUTER_NULL);
        }
        this.genres.add(genre);
    }




}
