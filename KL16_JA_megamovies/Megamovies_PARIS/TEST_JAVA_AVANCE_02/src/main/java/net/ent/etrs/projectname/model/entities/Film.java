package net.ent.etrs.projectname.model.entities;

import lombok.*;
import net.ent.etrs.projectname.model.entities.reference.Constantes;
import net.ent.etrs.projectname.model.entities.reference.Genre;
import net.ent.etrs.projectname.model.entities.reference.MessageErreur;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "FILM", uniqueConstraints = @UniqueConstraint(name = "UK__FILM", columnNames = {""}))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false, of = {"titre", "realisateur"})
@ToString(callSuper = true, of = {"dateSortie", "genre", "titre", "realisateur"})
public class Film extends AbstractEntity{

    @Getter
    @Setter
    @NonNull
    @NotNull(message = Constantes.FILM_DATE_SORTIE_NULL)
    @PastOrPresent(message = Constantes.FILM_DATE_SORTIE_FUTURE)
    @Column(name = "DATE_SORTIE", nullable = false)
    private LocalDate dateSortie;

    @Getter @Setter
    @NonNull
    @NotNull(message = MessageErreur.FILM_GENRE_NULL)
    @JoinColumn(name = "FILM_ID", nullable = false, foreignKey = @ForeignKey(name = "FK__GENRE__FILM_ID"))
    private Genre genre;

    @Getter
    @Setter
    @NonNull
    @NotBlank(message = Constantes.FILM_TITRE_NULL)
    @Size(max = Constantes.FILM_TITRE_TAILLE_MAX_VALEUR, message = Constantes.FILM_TITRE_TAILLE_MESSAGE)
    @Column(name = "TITRE", nullable = false, length = Constantes.FILM_TITRE_TAILLE_MAX_VALEUR)
    public String titre;

    @Getter @Setter
    @NonNull
    @NotNull(message = MessageErreur.REALISATEUR_FILM_NULL)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FILM_ID", nullable = false, foreignKey = @ForeignKey(name = "FK__REALISATEUR__FILM_ID"))
    private Realisateur realisateur;
}
