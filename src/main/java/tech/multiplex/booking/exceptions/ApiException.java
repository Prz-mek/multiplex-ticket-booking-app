package tech.multiplex.booking.exceptions;

public class ApiException extends IllegalArgumentException {

    public ApiException(String message) {
        super(message);
    }
}
