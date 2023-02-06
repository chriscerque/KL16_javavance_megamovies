package net.ent.etrs.ultramp3_GOUIN.model.facades;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.ultramp3_GOUIN.model.facades.impl.FacadeMetierCompositeurImpl;
import net.ent.etrs.ultramp3_GOUIN.model.facades.impl.FacadeMetierMusiqueImpl;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FacadeMetierFactory {

    public static FacadeMetierCompositeur fabriquerFacadeMetierCompositeur() {
        return new FacadeMetierCompositeurImpl();
    }

    public static FacadeMetierMusique fabriquerFacadeMetierMusique() {
        return new FacadeMetierMusiqueImpl();
    }

}

