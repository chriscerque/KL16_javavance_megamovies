package net.ent.etrs.megamovies.model.entities.references;

public class GenreException extends Exception {
	public GenreException() {
	}
	
	public GenreException(String message) {
		super(message);
	}
	
	public GenreException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public GenreException(Throwable cause) {
		super(cause);
	}
	
	public GenreException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
