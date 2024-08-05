package system.exception;

public class UserException extends Exception {
    public UserException(String errorMessage) {
        super(errorMessage);
        System.err.println("UserException: " + errorMessage);
    }
}
