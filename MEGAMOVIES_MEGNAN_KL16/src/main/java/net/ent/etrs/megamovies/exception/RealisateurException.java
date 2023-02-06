package net.ent.etrs.megamovies.exception;

public class RealisateurException extends Exception {

    public RealisateurException(String message) {
        super(message);
    }

    public RealisateurException(String message, Throwable cause) {
        super(message, cause);
    }

    public RealisateurException(Throwable cause) {
        super(cause);
    }

    public RealisateurException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
