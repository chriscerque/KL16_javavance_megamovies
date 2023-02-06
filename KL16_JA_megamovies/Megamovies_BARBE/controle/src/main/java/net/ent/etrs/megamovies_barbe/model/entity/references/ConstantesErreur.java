package net.ent.etrs.megamovies_barbe.model.entity.references;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;

// Lombok
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public final class ConstantesErreur {
    public static final String NOT_NULL = "La valeur ne doit pas être nulle";
    public static final String TAILLE_NON_RESPECTEE = "La taille doit être respectée";
}
