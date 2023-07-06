package tech.multiplex.booking.exceptions;

public class SeatAlreadyReservedException extends ApiException{
    public SeatAlreadyReservedException(String message) {
        super(message);
    }
}
