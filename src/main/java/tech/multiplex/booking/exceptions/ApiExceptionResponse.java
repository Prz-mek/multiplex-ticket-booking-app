package tech.multiplex.booking.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiExceptionResponse {
    private String message;
}
