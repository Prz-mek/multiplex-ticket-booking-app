package tech.multiplex.booking.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> validationError(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        final List<FieldError> fieldErrors = result.getFieldErrors();
        ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse(fieldErrors.stream().map(FieldError::getDefaultMessage).toList());
        return new ResponseEntity<>(validationErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(GapBetweenTakenSeatsIsForbiddenException.class)
    public ResponseEntity<Object> handleGapBetweenTakenSeatsIsForbiddenException(GapBetweenTakenSeatsIsForbiddenException e) {
        HttpStatus badRequest = HttpStatus.CONFLICT;
        ApiExceptionResponse apiException = new ApiExceptionResponse(e.getMessage());
        return new ResponseEntity<>(apiException, badRequest);
    }

    @ExceptionHandler(NoTicketsException.class)
    public ResponseEntity<Object> handleNoTicketsException(NoTicketsException e) {
        HttpStatus badRequest = HttpStatus.NO_CONTENT;
        ApiExceptionResponse apiException = new ApiExceptionResponse(e.getMessage());
        return new ResponseEntity<>(apiException, badRequest);
    }

    @ExceptionHandler(ReservationNotFoundException.class)
    public ResponseEntity<Object> handleReservationNotFoundException(ReservationNotFoundException e) {
        ApiExceptionResponse apiException = new ApiExceptionResponse(e.getMessage());
        return new ResponseEntity<>(apiException, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(ReservationTimePassedException.class)
    public ResponseEntity<Object> handleReservationTimePassedException(ReservationTimePassedException e) {
        ApiExceptionResponse apiException = new ApiExceptionResponse(e.getMessage());
        return new ResponseEntity<>(apiException, HttpStatus.GONE);
    }

    @ExceptionHandler(SeatAlreadyReservedException.class)
    public ResponseEntity<Object> handleSeatAlreadyReservedException(SeatAlreadyReservedException e) {
        ApiExceptionResponse apiException = new ApiExceptionResponse(e.getMessage());
        return new ResponseEntity<>(apiException, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ScreeningNotFoundException.class)
    public ResponseEntity<Object> handleScreeningNotFoundException(ScreeningNotFoundException e) {
        ApiExceptionResponse apiException = new ApiExceptionResponse(e.getMessage());
        return new ResponseEntity<>(apiException, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(value = {ApiException.class})
    public ResponseEntity<ApiExceptionResponse> handelApiException(ApiException e) {
        ApiExceptionResponse apiException = new ApiExceptionResponse(e.getMessage());
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }
}
