package net.ent.etrs.megamovies.model.facade.impl;

import net.ent.etrs.megamovies.model.daos.FilmDao;
import net.ent.etrs.megamovies.model.daos.RealisateurDao;
import net.ent.etrs.megamovies.model.daos.exceptions.BusinessException;
import net.ent.etrs.megamovies.model.daos.exceptions.DaoException;
import net.ent.etrs.megamovies.model.daos.impl.DaosFactory;
import net.ent.etrs.megamovies.model.entities.Realisateur;
import net.ent.etrs.megamovies.model.entities.references.Genre;
import net.ent.etrs.megamovies.model.facade.FacadeMetierRealisateur;
import org.apache.commons.collections4.IterableUtils;

import java.util.Set;
import java.util.stream.Collectors;

public class FacadeMetierRealisateurimpl implements FacadeMetierRealisateur {

    private RealisateurDao realisateurDao;

    protected FacadeMetierRealisateurimpl(){
        this.realisateurDao = DaosFactory.getRealisateurDao();
    }

    @Override
    public Set<Realisateur> readAll() throws BusinessException {
       try {
           return IterableUtils.toList(this.realisateurDao.findAll()).stream().collect(Collectors.toSet());
       }catch (DaoException e){
           throw new BusinessException(e.getMessage(),e);
       }

    }

    @Override
    public Realisateur save(final Realisateur realisateur) throws BusinessException {
        try {
            return this.realisateurDao.save(realisateur).orElseThrow(() -> new BusinessException("Could not save film"));
        }catch (DaoException e ){
            throw new BusinessException(e.getMessage(),e);
        }
    }

    @Override
    public void delete(final Realisateur realisateur) throws BusinessException {
        try {
            this.realisateurDao.delete(realisateur);
        }catch (DaoException e ){
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public Set<Realisateur> findByGenre(final Genre genre) throws BusinessException {
        try {
            return this.realisateurDao.findByGenre(genre);
        }catch (DaoException e ){
            throw new BusinessException(e.getMessage(),e);
        }
    }
}
