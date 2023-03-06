package net.ent.etrs.megamovies_barbe.model.exceptions;

public class BusinessException extends Exception {


    public BusinessException() {
    }


    public BusinessException(final String message) {
        super(message);
    }


    public BusinessException(final String message, final Throwable cause) {
        super(message, cause);
    }


    public BusinessException(final Throwable cause) {
        super(cause);
    }


    public BusinessException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}