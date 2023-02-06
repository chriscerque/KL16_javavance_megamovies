package net.ent.etrs.template.model.entities;


import lombok.*;
import net.ent.etrs.template.model.entities.references.Constante;
import net.ent.etrs.template.model.entities.references.Genre;
import net.ent.etrs.template.model.entities.references.MessageErreur;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "REALISATEUR", uniqueConstraints = @UniqueConstraint(name = "REALISATEUR__NOM__UK", columnNames = "NOM"))

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false, of = {"nom"})
@ToString(callSuper = true, of = {"ID"})

public class Realisateur extends AbstractEntity{


    //LB
    @Getter @Setter
    @NonNull
    //BV
    @NotBlank(message = MessageErreur.REALISATEUR_NOM_NULL)
    @Length(max = Constante.REALISATEUR_NOM_TAILLE_MAX, message = MessageErreur.REALISATEUR_NOM_TAILLE_MAX)
    //JPA
    @Column(name = "NOM", nullable = false, length = Constante.REALISATEUR_NOM_TAILLE_MAX)
    private String nom;


    //JPA
    @Enumerated(EnumType.STRING)
    @ElementCollection
    @JoinTable(name = "REALISATEUR_GENRE", foreignKey = @ForeignKey(name = "REALISATEUR_ID"))
    private Set<Genre> genres = new HashSet<>();



}
