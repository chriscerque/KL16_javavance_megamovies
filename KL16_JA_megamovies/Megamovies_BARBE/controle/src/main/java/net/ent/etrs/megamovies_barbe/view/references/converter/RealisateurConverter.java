package net.ent.etrs.megamovies_barbe.view.references.converter;


import javafx.util.StringConverter;
import lombok.NonNull;
import net.ent.etrs.megamovies_barbe.model.entity.Realisateur;
import net.ent.etrs.megamovies_barbe.model.exceptions.BusinessException;
import net.ent.etrs.megamovies_barbe.model.facades.FacadeMetierRealisateur;
import net.ent.etrs.megamovies_barbe.model.facades.FacadeMetierRealisateurImpl;
import net.ent.etrs.megamovies_barbe.view.references.ConstantesView;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RealisateurConverter extends StringConverter<Realisateur> {
    public static final FacadeMetierRealisateur FACADE_METIER_REALISATEUR;
    private static Log log = LogFactory.getLog("LoggerInit");

    static {
        FACADE_METIER_REALISATEUR = new FacadeMetierRealisateurImpl();
    }

    @Override public String toString(@NonNull Realisateur realisateur) {
        return realisateur.getNom();
    }

    @Override public Realisateur fromString(String s) {


        try {
            if (FACADE_METIER_REALISATEUR.findByName(s).isPresent()) {
                return FACADE_METIER_REALISATEUR.findByName(s).get();
            } else {
                log.error(ConstantesView.NAME_NOT_PRESENT);
            }
        } catch (BusinessException e) {
            log.error(ConstantesView.BUSINESS_EXCEPTION);
        }
        return null;
    }
}
