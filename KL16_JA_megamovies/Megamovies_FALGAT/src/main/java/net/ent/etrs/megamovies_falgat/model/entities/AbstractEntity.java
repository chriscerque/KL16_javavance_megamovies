package net.ent.etrs.megamovies_falgat.model.entities;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@ToString(of = {"id"})
public abstract class AbstractEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @Getter
    private Long id;
}
