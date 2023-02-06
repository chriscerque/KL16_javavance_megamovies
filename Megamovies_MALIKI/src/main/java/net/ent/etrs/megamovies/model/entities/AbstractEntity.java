package net.ent.etrs.megamovies.model.entities;

import lombok.Getter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class AbstractEntity implements Serializable {
	// annotation lombok
	@Getter
	// annotations JPA
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	
}
