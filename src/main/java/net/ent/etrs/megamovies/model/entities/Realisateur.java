package net.ent.etrs.megamovies.model.entities;

import lombok.*;
import net.ent.etrs.megamovies.model.references.ConstantesMetier;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "REALISATEUR", uniqueConstraints = @UniqueConstraint(name = "REALISATEUR__NOM__UK", columnNames = "NOM"))


@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Realisateur extends AbstractEntity {
    @Getter
    @Setter

    @NotBlank(message = ConstantesMetier.MSG_REALISATEUR_NOM_VIDE)
    @Size(min = 3, message = ConstantesMetier.MSG_REALISATEUR_NOM_TAILLE)
    @Column(name = "NOM", nullable = false)
    private String nom;


}
