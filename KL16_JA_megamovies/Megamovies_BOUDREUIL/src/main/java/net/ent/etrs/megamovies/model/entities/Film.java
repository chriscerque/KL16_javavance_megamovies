package net.ent.etrs.megamovies.model.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import net.ent.etrs.megamovies.model.entities.references.Constante;
import net.ent.etrs.megamovies.model.entities.references.Genre;
import net.ent.etrs.megamovies.model.entities.references.MessageErreur;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;


// LB
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "titre", callSuper = false)
@ToString(of = {"date_sortie", "titre", "genre", "realisateur"}, callSuper = true)
// JPA
@Entity
@Table(name = "FILM", uniqueConstraints = @UniqueConstraint(name = "film__titre__uk", columnNames = "TITRE"))
public class Film extends AbstractEntity {

    //LB
    @Getter @Setter
    @NonNull
    //BV
    @NotBlank(message = MessageErreur.FILM_TITRE_NULL)
    @Length(max = Constante.FILM_TITRE_TAILLE_MAX, message = MessageErreur.FILM_TITRE_TAILLE_MAX)
    //JPA
    @Column(name = "TITRE", nullable = false, length = Constante.FILM_TITRE_TAILLE_MAX)
    private String titre;
    
    //LB
    @Getter 
    @Setter
    @NonNull
    //BV
    @NotNull(message = MessageErreur.FILM_DATE_SORTIE_NULL)
    //JPA
    @Column(name = "DATE_SORTIE", nullable = false)
    private LocalDate dateSortie;
    
    //LB
    @Getter
    @Setter
    @NonNull
    //BV
    @NotNull(message = MessageErreur.FILM_GENRE_NULL)
    @Length(max = Constante.FILM_GENRE_TAILLE_MAX, message = MessageErreur.FILM_GENRE_TAILLE_MAX)
    //JPA
    @Enumerated(EnumType.STRING)
    @Column(name = "GENRE", nullable = false)
    private Genre genre;

    //LB
    @Getter @Setter
    @NonNull
    //BV
    @NotNull(message = MessageErreur.FILM_REALISATEUR_NULL)
    //JPA
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "REALISATEUR_ID", nullable = false, foreignKey = @ForeignKey(name = "FK__FILM__REALISATEUR_ID"))
    private Realisateur realisateur;
}
