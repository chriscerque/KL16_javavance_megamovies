package net.ent.etrs.templatefx.model.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id"})
public abstract class AbstractEntity implements Serializable {

    //LBK
    @Getter
    //JPA
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
