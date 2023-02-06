package net.ent.etrs.Megamovies_SANTOS.model.model.entities;








//@EqualsAndHashCode : pour générer PROPREMENT equals et hashCode



//@Log / @CommonsLog : pour obtenir facilement un log

//@NoArgsConstructor: génère le constructeur sans argument et public ;

//@AllArgsConstructor: génère le constructeur avec tous les arguments et public (pour l'exemple) ;

// @Max / @Min Valeur Max Min pour une Variable, Attention seulement les int bool ......

//@Size Valeur Max Min pour une Variable, Attention seulement les listes

//@Length Valeur Max Min pour une Variable, Attention seulement les Chars

//@Getter : génère tous les getters sur les champs;
//@Getter(lazy = true) : pour calculer et conserver la valeur d’un champ seulement à l’invocation du getter



//@Setter : génère tous les setters sur les champs;

//@EqualsAndHashCode(of=...): génère equals et hashCode (et d'autres méthodes) sur les champs donnés ;

//@ToString(of=...): génère toString sur les champs donnés.

//@NonNull : indique au Builder tous les champs obligatoires;


import lombok.*;
import net.ent.etrs.Megamovies_SANTOS.model.model.entities.reference.Constante;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "Variable Unique")
@ToString(of = {"id", "TITRE ", "DATE_SORTIE ", "GENRE ", "REALISATEUR "})

@Entity
@Table(name = "FILM", uniqueConstraints = @UniqueConstraint(name = "film__titre__uk", columnNames = {"TITRE"}))
public class Film extends AbstractEntity{
    //LB
    @Getter
    @Setter
    @NonNull
    //BV
    @NotEmpty(message = Constante.FILM_TITRE_NULL)
    @Length(max = Constante.FILM_TITRE_TAILLE_MAX, message = Constante.FILM_TITRE_TAILLE_MAX_MESSAGE)
    //JPA
    @Column(name = "TITRE", nullable = false, length = Constante.FILM_TITRE_TAILLE_MAX)
    private String titre;



    @Getter
    @Setter
    @NonNull
    //BV
    @PastOrPresent(message = Constante.DATE_FUTUR)
    @Column(name = "DATE_SORTIE", nullable = false)
    //JPA
    private LocalDate dateSortie;



    @Getter @Setter
    @NonNull
    //BV
    @NotNull(message = Constante.FILM_GENRE_NULL)
    //JPA
    @Enumerated(EnumType.STRING)
    @Column(name = "GENRE", length = Constante.GENRE_TAILLE_MAX, nullable = false)
    private Genre genre ;



    @Getter @Setter
    @NonNull
    //BV
    @NotNull(message = Constante.FILM_REA_NULL)
    //JPA
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "REALISATEUR_ID", nullable = false, foreignKey = @ForeignKey(name = "film__realisateur_fk"))
    private Realisateur realisateur ;

}
