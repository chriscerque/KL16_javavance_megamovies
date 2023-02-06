package net.ent.etrs.megamovies.model.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@ToString(of = "id")
@NoArgsConstructor
public abstract class AbstractEntity implements Serializable {


    //    @Getter
//    @Column(name = "CREATED_AT")
//    public LocalDate dateCreated;
//    @Getter
//    @Column(name = "UPDATED_AT")
//    public LocalDate dateUpdated;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @Getter
    private Long id;

//    @PrePersist
//    protected void prePersist() {
//        this.dateCreated = LocalDate.now();
//        this.dateUpdated = this.dateCreated;
//    }
//
//    @PreUpdate
//    protected void preUpdate() {
//        this.dateUpdated = LocalDate.now();
//    }

}
