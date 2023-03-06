package net.ent.etrs.megamovies.model.facade.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.megamovies.model.facade.FacadeMetierFilm;
import net.ent.etrs.megamovies.model.facade.FacadeMetierRealisateur;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FacadeFactory {

//    public static ClientFacade fabriquerClientFacade(){
//        return new ClientFacadeImpl();
//    }
//
//    public static CommandeFacade fabriquerCommandeFacade(){
//        return new CommandeFacadeImpl();
//    }
//
//    public static ProduitFacade fabriquerProduitFacade(){
//        return new ProduitFacadeImpl();
//    }
        public static FacadeMetierFilm fabriquerFilmFacade(){
        return new FacadeMetierFilmImpl();
    }

    public static FacadeMetierRealisateur fabriquerRealisateur(){
            return new FacadeMetierRealisateurimpl();
    }

}
