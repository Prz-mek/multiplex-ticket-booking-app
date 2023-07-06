package tech.multiplex.booking.exceptions;

public class ReservationTimePassedException extends ApiException{
    public ReservationTimePassedException(String message) {
        super(message);
    }
}
