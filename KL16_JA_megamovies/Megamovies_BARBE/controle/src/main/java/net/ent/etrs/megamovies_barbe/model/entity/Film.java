package net.ent.etrs.megamovies_barbe.model.entity;


import lombok.*;
import net.ent.etrs.megamovies_barbe.model.entity.references.Constantes;
import net.ent.etrs.megamovies_barbe.model.entity.references.ConstantesErreur;
import net.ent.etrs.megamovies_barbe.model.entity.references.Genre;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

// Java Persistence API
@Entity
@Table(name = "FILM", uniqueConstraints = @UniqueConstraint(name = "film__titre__uk",
        columnNames = {"titre"}))
// Lombok
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false, of = {"titre"})
@ToString(callSuper = true, of = {"dateSortie", "dateSortie", "genre", "titre", "realisateur"})
public class Film extends AbstractEntity {

    // Lombok
    @NonNull @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC)
    // Bean Validation
    @NotNull(message = ConstantesErreur.NOT_NULL)
    @Past
    // Java Persistence API
    @Column(name = "DATE_SORTIE", nullable = false)
    private LocalDate dateSortie;

    // Lombok
    @NonNull @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC)
    // Bean Validation
    @NotNull(message = ConstantesErreur.NOT_NULL)
    // Java Persistence API
    @Enumerated(EnumType.STRING)
    @Column(name = "GENRE", nullable = false, length = 50)
    private Genre genre;

    // Lombok
    @NonNull @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC)
    // Bean Validation
    @NotNull(message = ConstantesErreur.NOT_NULL)
    @Size(min = Constantes.TAILLE_MIN_TITRE, max = Constantes.TAILLE_MAX_TITRE, message = ConstantesErreur.TAILLE_NON_RESPECTEE)
    @NotBlank
    // Java Persistence API
    @Column(name = "TITRE", nullable = false, length = 150)
    private String titre;

    // Lombok
    @NonNull @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC)
    // Bean Validation
    @NotNull(message = ConstantesErreur.NOT_NULL)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REALISATEUR_ID", foreignKey = @ForeignKey(name = "film__realisateur_fk"))
    private Realisateur realisateur;


}
