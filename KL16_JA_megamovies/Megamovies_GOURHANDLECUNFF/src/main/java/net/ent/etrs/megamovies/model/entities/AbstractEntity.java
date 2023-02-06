package net.ent.etrs.megamovies.model.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@SuppressWarnings("serial")

@MappedSuperclass
@ToString(of = {"id"})
public abstract class AbstractEntity implements Serializable {
    // annotation lombok
    @Getter
    // annotations JPA
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
