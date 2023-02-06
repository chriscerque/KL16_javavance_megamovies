package net.ent.etrs.megamovies_barbe.model.dao.impl;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.megamovies_barbe.model.dao.DaoFilm;
import net.ent.etrs.megamovies_barbe.model.dao.DaoRealisateur;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DaoFactory {

   private static final DaoFilm DAO_FILM;
   private static final DaoRealisateur DAO_REALISATEUR;

   static {
      DAO_FILM = new DaoFilmJpaImpl();
   }

   static {
      DAO_REALISATEUR = new DaoRealisateurJpaImpl();
   }

   public static DaoFilm fabriquerDaoFilmJpaFactory() {
      return DAO_FILM;
   }

   public static DaoRealisateur fabriquerDaoRealisateurJpaFactory() {
      return DAO_REALISATEUR;
   }

}
