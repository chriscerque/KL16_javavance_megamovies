package net.ent.etrs.template.model.entities.references;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor(access = AccessLevel.PUBLIC)
@ToString(callSuper = false, of = {"nom"})
@EqualsAndHashCode(callSuper = false)
public class Realisateur {


    public Realisateur(String nom, Set<Genre> setGenres) {
        this.nom = nom;
        this.setGenres = setGenres;
    }

    //LBK
    @Getter @Setter
    //BV
    @NotBlank( /* message = C.ATTRIBUT_MOTIF */)
    @Size( /* max = 500, message = C.ATTRIBUT_MOTIF */)
    private String nom;



    //BV
    @NotBlank( /* message = C.ATTRIBUT_MOTIF */)
    @Size( /* max = 500, message = C.ATTRIBUT_MOTIF */)
    private Set<Genre> setGenres = new HashSet<Genre>(1);

}
