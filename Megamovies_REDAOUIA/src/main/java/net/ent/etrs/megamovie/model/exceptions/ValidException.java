package net.ent.etrs.megamovie.model.exceptions;

public class ValidException extends Exception {
    public ValidException(String message) {
        super(message);
    }

    public ValidException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidException(Throwable cause) {
        super(cause);
    }

    protected ValidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
