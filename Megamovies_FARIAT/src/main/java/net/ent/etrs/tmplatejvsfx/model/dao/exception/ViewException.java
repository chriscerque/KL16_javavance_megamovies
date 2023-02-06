package net.ent.etrs.tmplatejvsfx.model.dao.exception;

public class ViewException extends Exception{
    public ViewException(final String message) {
        super(message);
    }

    public ViewException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ViewException(final Throwable cause) {
        super(cause);
    }

    public ViewException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
