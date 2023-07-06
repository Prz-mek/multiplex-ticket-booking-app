package tech.multiplex.booking.exceptions;

public class ScreeningNotFoundException extends ApiException {
    public ScreeningNotFoundException(String message) {
        super(message);
    }
}
