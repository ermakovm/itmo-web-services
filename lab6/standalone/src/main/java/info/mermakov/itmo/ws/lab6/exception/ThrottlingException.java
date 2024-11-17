package info.mermakov.itmo.ws.lab6.exception;

public class ThrottlingException extends RuntimeException {
    public ThrottlingException(final String message) {
        super(message);
    }
}
