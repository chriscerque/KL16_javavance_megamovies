package net.ent.etrs.megamovies.model.entities;

import lombok.*;
import net.ent.etrs.megamovies.model.entities.references.ConstantesMetier;
import net.ent.etrs.megamovies.model.entities.references.Genre;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false, of = {"titre"})
@ToString(callSuper = true, of = {"titre", "genre", "dateSortie", "realisateur"})
@Entity
@Table(name = "FILM", uniqueConstraints = @UniqueConstraint(name = "FILM__TITRE__UK", columnNames = {"TITRE"}))
public class Film extends AbstractEntity {
	
	
	@Getter
	@Setter
	@FutureOrPresent(message = ConstantesMetier.MSG__DATE_NON_VALIDE)
	@NotNull(message = ConstantesMetier.MSG__DATE_NON_VALIDE)
	@Column(name = "DATE_SORTIE", nullable = false)
	private LocalDate dateSortie;
	
	@Getter
	@Setter
	@NotNull(message = ConstantesMetier.FILM_GENRE_NON_RENSEIGNE)
	@Enumerated(EnumType.STRING)
	@Column(name = "GENRE", nullable = false)
	private Genre genre;
	
	@Getter
	@Setter
	@NotNull(message = ConstantesMetier.MSG_FILM_TITRE_NON_RENSEIGNE)
	@NotEmpty(message = ConstantesMetier.MSG_FILM_TITRE_NON_RENSEIGNE)
	@Length(min = ConstantesMetier.FILM_TITRE_TAILLE_MIN, max = ConstantesMetier.FILM_TITRE_TAILLE_MAX, message = ConstantesMetier.MSG_FILM_TITRE_TAILLE_ERROR)
	@Column(name = "TITRE", length = ConstantesMetier.REALISATEUR_NOM_TAILLE_MAX, nullable = false)
	private String titre;
	
	
	@Getter
	@Setter
	@NotNull(message = ConstantesMetier.FILM_REALISATEUR_NON_RENSEIGNE)
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REALISATEUR_ID", nullable = false, foreignKey = @ForeignKey(name = "REALISATEUR_ID"))
	private Realisateur realisateur;
	
}
