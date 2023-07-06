package tech.multiplex.booking.exceptions;

public class ReservationNotFoundException extends ApiException {
    public ReservationNotFoundException(String message) {
        super(message);
    }
}
