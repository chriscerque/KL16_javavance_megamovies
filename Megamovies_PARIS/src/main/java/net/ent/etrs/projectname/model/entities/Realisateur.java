package net.ent.etrs.projectname.model.entities;

import lombok.*;
import net.ent.etrs.projectname.model.entities.reference.Constantes;
import net.ent.etrs.projectname.model.entities.reference.Genre;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "REALISATEUR", uniqueConstraints = @UniqueConstraint(name = "UK__REALISATEUR", columnNames = {""}))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false, of = {"nom"})
@ToString(callSuper = true, of = {"nom", "genres"})
public class Realisateur extends AbstractEntity{

    @Getter
    @Setter
    @NonNull
    @NotBlank(message = Constantes.NOM_NOT_NULL)
    @Size(max = Constantes.FILM_NOM_TAILLE_MAX_VALEUR, message = Constantes.FILM_NOM_TAILLE_MESSAGE)
    @Column(name = "NOM", nullable = false, length = Constantes.FILM_NOM_TAILLE_MAX_VALEUR)
    public String nom;

    @Getter
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "REALISATEUR_GENRE", joinColumns = @JoinColumn(name = "FILM_ID", foreignKey = @ForeignKey(name = "FK__REALISATEUR_GENRE__REALISATEUR_ID")))
    @Column(name = "GENRE")
    private Set<Genre> genres = new HashSet<>();


}
