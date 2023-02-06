package net.ent.etrs.ultramp3_GOUIN.model.daos.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.ultramp3_GOUIN.model.daos.CompositeurDAO;
import net.ent.etrs.ultramp3_GOUIN.model.daos.MusiqueDAO;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DaosFactory {
    public static CompositeurDAO fabriquerCompositeurDAO() {
        return CompositeurDAOImpl.getInstance();
    }
    public static MusiqueDAO fabriquerMusiqueDAO() {
        return MusiqueDAOImpl.getInstance();
    }

}
