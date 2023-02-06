package net.ent.etrs.ultramp3_GOUIN.model.entities;


import lombok.*;
import net.ent.etrs.ultramp3_GOUIN.model.entities.references.ConstanteModel;
import net.ent.etrs.ultramp3_GOUIN.model.entities.references.Genre;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "COMPOSITEUR", uniqueConstraints = @UniqueConstraint(name = "COMPOSITEUR__NOM__UK", columnNames = "NOM"))
@ToString(callSuper = true, of = "nom")
@EqualsAndHashCode(callSuper = false, of = {"nom"})
public class Compositeur extends AbstractEntity {

    //LBK
    @Getter @Setter
    //BV
    @NotBlank(message = ConstanteModel.MSG_NOM_NON_VIDE)
    @Size(min = ConstanteModel.COMPOSITEUR_NOM_TAILLE_MIN, max = ConstanteModel.COMPOSITEUR_NOM_TAILLE_MAX, message = ConstanteModel.MSG_NOM_TAILLE_MIN_MAX)
    //JPA
    @Column(name = "NOM", nullable = false, length = ConstanteModel.COMPOSITEUR_NOM_TAILLE_MAX)
    private String nom;


    //jpa
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "COMPOSITEUR_GENRE", joinColumns = @JoinColumn(name = "COMPOSITEUR_ID", foreignKey = @ForeignKey(name = "FK_COMPOSITEUR_GENRE_COMPOSITEUR_ID")))
    @Column(name = "GENRE", nullable = false, length = ConstanteModel.GENRE_TAILLE_MAX)
    Set<Genre> genres;

    private Set<Genre> getAllGenre(){
        return Collections.unmodifiableSet(genres);
    }
}
