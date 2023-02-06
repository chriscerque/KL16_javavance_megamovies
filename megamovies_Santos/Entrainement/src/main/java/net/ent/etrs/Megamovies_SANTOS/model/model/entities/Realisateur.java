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
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor(access =  AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "NOM")
@ToString(of = {"id", "NOM ", "genres "})
@Entity
@Table(name = "REALISATEUR",uniqueConstraints = @UniqueConstraint(name ="realisateur__nom__uk",columnNames = "NOM" ))

public class Realisateur extends AbstractEntity   {
    //LB
    @Getter
    @Setter
    @NonNull
    //BV
    @NotEmpty(message = Constante.REALISATEUR_NOM_NULL)
    @Length(max = Constante.REALISATEUR_NOM_TAILLE_MAX, message = Constante.REALISATEUR_NOM_TAILLE_MAX_MESSAGE)
    //JPA
    @Column(name = "NOM", nullable = false, length = Constante.REALISATEUR_NOM_TAILLE_MAX)
    private String nom;




    @ElementCollection()
    @JoinTable(name = "REALISATEUR_GENRE", joinColumns = @JoinColumn(name = "REALISATEUR_ID"), inverseJoinColumns = @JoinColumn(name = "GENRE"))
    @Column(name = "GENRE", nullable = false)
    @Enumerated(EnumType.STRING)
    private final Set<Genre> genres = new HashSet<>();



}
