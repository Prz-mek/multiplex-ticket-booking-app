package tech.multiplex.booking.exceptions;

public class NoTicketsException extends ApiException {
    public NoTicketsException(String message) {
        super(message);
    }
}
