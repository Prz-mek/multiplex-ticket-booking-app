package tech.multiplex.booking.exceptions;

public class GapBetweenTakenSeatsIsForbiddenException extends ApiException{
    public GapBetweenTakenSeatsIsForbiddenException(String message) {
        super(message);
    }
}
