package africa.semicolon.E_Transit.exception;

public class UserNotFoundException extends BusinessLogicException{
    public UserNotFoundException(String message) {
        super(message);
    }
}
