package quackrbackend.exceptions;

public class DuplicatedException extends RuntimeException {
    public DuplicatedException(String message) {
        super(message);
    }
}
