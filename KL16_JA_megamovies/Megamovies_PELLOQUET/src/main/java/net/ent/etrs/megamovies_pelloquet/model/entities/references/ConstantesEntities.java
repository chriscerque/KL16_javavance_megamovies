package net.ent.etrs.megamovies_pelloquet.model.entities.references;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConstantesEntities {
    public static final String MESSAGE_NOM_NULL = "Le nom ne doit pas être null.";
    public static final String MESSAGE_TAILLE_NOM = "Le nom doit contenir entre 2 et 50 caractères.";
    public static final String MESSAGE_TITRE_NULL = "Le titre ne doit pas être null.";
    public static final String MESSAGE_TAILLE_TITRE = "Le titre doit contenir entre 1 et 100 caractères.";
    public static final String MESSAGE_DATE_NULL = "La date de sortie ne peut pas être null.";
}
