package net.ent.etrs.megamovies.model.entities;

import lombok.*;
import net.ent.etrs.megamovies.model.entities.references.ConstantesMetier;
import net.ent.etrs.megamovies.model.entities.references.Genre;
import net.ent.etrs.megamovies.model.entities.references.GenreException;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false, of = {"nom"})
@ToString(callSuper = true, of = {"nom"})
@Entity
@Table(name = "REALISATEUR", uniqueConstraints = @UniqueConstraint(name = "REALISATEUR__NOM__UK", columnNames = {"NOM"}))
public class Realisateur extends AbstractEntity {
	
	
	@Getter
	@Setter
	@NotNull(message = ConstantesMetier.MSG_REALISATEUR_NOM_NON_VIDE)
	@NotEmpty(message = ConstantesMetier.MSG_REALISATEUR_NOM_NON_VIDE)
	@Length(min = ConstantesMetier.REALISATEUR_NOM_TAILLE_MIN, max = ConstantesMetier.REALISATEUR_NOM_TAILLE_MAX, message = ConstantesMetier.MSG_REALISATEUR_NOM_ERROR)
	@Column(name = "NOM", length = ConstantesMetier.REALISATEUR_NOM_TAILLE_MAX, nullable = false)
	private String nom;
	
	@Enumerated(EnumType.STRING)
	@ElementCollection
	@CollectionTable(name = "REALISATEUR_GENRE", joinColumns = @JoinColumn(name = "REALISATEUR_ID", foreignKey = @ForeignKey(name = "FK__REALISATEUR_GENRE__REALISATEUR_ID")))
	@Column(name = "GENRE")
	private Set<Genre> genres = new HashSet<>();
	
	
	public void ajouterGenre(final Genre genre) throws GenreException {
		if (Objects.isNull(genre)) {
			throw new GenreException();
		}
		if (this.genres.contains(genre)) {
			throw new GenreException();
		}
		this.genres.add(genre);
	}
	
	
	public void supprimerGenre(final Genre genre) throws GenreException {
		if (Objects.isNull(genre)) {
			throw new GenreException();
		}
		if (!this.genres.contains(genre)) {
			throw new GenreException();
		}
		this.genres.remove(genre);
	}
	
	
	public Set<Genre> getLstGenre() {
		return Collections.unmodifiableSet(genres);
	}
}
