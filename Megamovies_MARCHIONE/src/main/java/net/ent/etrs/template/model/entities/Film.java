package net.ent.etrs.template.model.entities;

import lombok.*;
import net.ent.etrs.template.model.entities.references.Genre;
import net.ent.etrs.template.model.entities.references.Realisateur;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "FILM", uniqueConstraints = @UniqueConstraint(name = "UK__FILM__TITRE", columnNames = {"TITRE"}))

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = false, of = {"dateNaissance","nom","prenom"})
public class Film extends AbstractEntity {


    public Film(LocalDate dateSortie, String titre, String s, Genre genre, Realisateur realisateur) {
        this.dateSortie = dateSortie;
        this.titre = titre;
        this.genre = genre;
        this.realisateur = realisateur;
    }

    //LBK
    @Getter @Setter
    //BV
    @NotBlank( message = "Veuillez renseinger la date de sortie.")
    @Past(message = "La date de sortie ne peut etre dans le futur.")
    //JPA
    @Column(name = "DATE_SORTIR", nullable = false, length = 50)
    private LocalDate dateSortie;


    //LBK
    @Getter @Setter
    //BV
    @NotBlank(  message = "Veuillez renseingerle titre.")
    @Size(max = 50, message = "La taille du titre ne peut pas depasser 50 caracteres.")
    //JPA
    @Column(name = "TITRE", nullable = false, length = 50)
    private String titre;


    //LBK
    @Getter @Setter
    //BV
    @NotBlank( message = "Veuillez renseinger le genre.")
    @Size( max = 20, message = "Le genre ne peut depasser 20 caracteres.")
    //JPA
    @Column(name = "GENRE", nullable = false, length = 50)
    private Genre genre;


    //LBK
    @Getter @Setter
    //BV
    @NotBlank(message = "Veuillez renseinger le realisateur.")
    @Size(max = 50, message = "Le realisateur ne peut depasser 50 caracteres.")
    //JPA
    @Column (name = "REALISATEUR", nullable = false, length = 100)
    private Realisateur realisateur;





    // Ajouter un Judoka

//    public void ajouterJudokat(Judoka judoka) throws ValidException {
//        if (Object.isNull(judoka)) {
//            throw new ValidException(C.JUDOKA_AJOUTER_EXCEPTION);
//        }
//        this.judokas.add(judoka);
//    }



    // Delete un judoka

//    public void supprimerJudoka(Judoka judoka) throws ValidException {
//        if (Objects.isNull(categorie)) {
//            throw new ValidException(C.JUDOKA_DELETE_EXCEPTION);
//        }
//        this.judokas.remove(categorie);
//    }





}
