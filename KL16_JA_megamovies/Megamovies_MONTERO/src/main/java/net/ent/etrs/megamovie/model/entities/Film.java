package net.ent.etrs.megamovie.model.entities;


import lombok.*;
import net.ent.etrs.megamovie.model.entities.references.Constantes;
import net.ent.etrs.megamovie.model.entities.references.Genre;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "FILM", uniqueConstraints = @UniqueConstraint(name = "FILM__TITRE__UK", columnNames = {"TITRE"}))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"titre"}, callSuper = false)
@ToString(of = {"titre", "dateSortie"}, callSuper = true)
public class Film extends AbstractEntity {

    // LB
    @Getter
    @Setter
    // BV
    @NotBlank(message = Constantes.FILM_TITRE_NULL)
    @Size(max = Constantes.FILM_TITRE_TAILLE_MAX_VALEUR, message = Constantes.FILM_TITRE_TAILLE_MESSAGE)
    // JPA
    @Column(name = "TITRE", nullable = false, length = Constantes.FILM_TITRE_TAILLE_MAX_VALEUR)
    public String titre;

    // LB
    @Getter
    @Setter
    // BV
    @NotNull(message = Constantes.FILM_GENRE_NULL)
    // JPA
    @Enumerated(EnumType.STRING)
    @Column(name = "GENRE", nullable = false)
    public Genre genre;

//    // OPTIONNELLE VOIR BONUS 2 -> PU_DC
//    // LB
//    @Getter
//    @Setter
//    // BV
//    @NotBlank(message = Constantes.FILM_IDVIDEO_NULL)
//    // JPA
//    @Column(name = "IDVIDEO", nullable = false)
//    public String idVideo;

    // LB
    @Getter
    @Setter
    // BV
    @NotNull(message = Constantes.FILM_DATE_SORTIE_NULL)
    // JPA
    @Column(name = "DATE_SORTIE", nullable = false)
    private LocalDate dateSortie;
    
    // LB
    @Getter
    @Setter
    // BV
    @NotNull(message = Constantes.FILM_REALISATEUR_NULL)
    // JPA
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REALISATEUR_ID", nullable = false, foreignKey = @ForeignKey(name = "FILM__REALISATEUR_FK"))
    private Realisateur realisateur;

}
