package tech.multiplex.booking.exceptions;

public class SeatsAreTakenException extends ApiException{
    public SeatsAreTakenException(String message) {
        super(message);
    }
}
