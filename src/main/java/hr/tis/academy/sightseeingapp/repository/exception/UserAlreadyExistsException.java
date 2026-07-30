package hr.tis.academy.sightseeingapp.repository.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
    public UserAlreadyExistsException(Exception e) {super(e);}
}
