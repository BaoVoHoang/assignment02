package system.exception;

public class BookException extends Exception {
    public BookException(String errorMessage) {
        super(errorMessage);
        System.err.println("BookException: " + errorMessage);
    }
}
