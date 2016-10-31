package edu.aurora.oilchange;

/**
 * Thrown to indicate the given date is invalid.
 */
public class InvalidDateException extends RuntimeException {
    /**
     * Creates a new exception with no detail message
     */
    public InvalidDateException() {
        super();
    }

    /**
     * Creates a new exception with a detail message describing the exception
     *
     * @param message the message describing the exception
     */
    public InvalidDateException(String message) {
        super(message);
    }

    /**
     * Creates a new exception with a detail message and cause of the exception
     *
     * @param message the message describing the exception
     * @param cause   the cause of the exception
     */
    public InvalidDateException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates a new exception showing what caused the exception
     *
     * @param cause the cause of the exception
     */
    public InvalidDateException(Throwable cause) {
        super(cause);
    }
}
