package net.ent.etrs.ultramp3_GOUIN.model.entities;

import lombok.*;
import net.ent.etrs.ultramp3_GOUIN.model.entities.references.ConstanteModel;
import net.ent.etrs.ultramp3_GOUIN.model.entities.references.Genre;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true, of = {"dateSortie", "titre", "genre", "compositeur"})
@EqualsAndHashCode(callSuper = false, of = {"titre", "dateSortie", "genre"})
@Entity
@Table(name = "MUSIQUE", uniqueConstraints = @UniqueConstraint(name = "MUSIQUE_TITRE_UK", columnNames = "TITRE"))
public class Musique extends AbstractEntity {

    //LBK
    @Getter @Setter

    //BV
    @NotNull(message = ConstanteModel.MSG_MUSIQUE_DATESORTIE_NON_NULL)
    //JPA
    @Column(name = "DATE_SORTIE", nullable = false)
    private LocalDate dateSortie;

    //LBK
    @Getter @Setter
    //BV
    @NotNull(message = ConstanteModel.MSG_TITRE_NON_VIDE)
    @NotBlank(message = ConstanteModel.MSG_TITRE_NON_VIDE)
    @Size(min = ConstanteModel.MUSIQUE_TITRE_TAILLE_MIN ,max = ConstanteModel.MUSIQUE_TITRE_TAILLE_MAX, message = ConstanteModel.MSG_TITRE_TAILLE_MAX)
   //JPA
    @Column(name = "TITRE", nullable = false, length = ConstanteModel.MUSIQUE_TITRE_TAILLE_MAX)
    private String titre;

    //LBK
    @Getter @Setter
    //BV
    @NotNull
    //JPA
    @Enumerated(EnumType.STRING)
    @Column(name = "GENRE", nullable = false, length = ConstanteModel.GENRE_TAILLE_MAX)
    private Genre genre;

    //LBK
    @Getter @Setter
    //BV
    @NotNull
    //JPA
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "COMPOSITEUR_ID", nullable = false, foreignKey = @ForeignKey(name = "MUSIQUE__COMPOSITEUR_FK"))
    private Compositeur compositeur;

}
