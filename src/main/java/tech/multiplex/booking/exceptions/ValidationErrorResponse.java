package tech.multiplex.booking.exceptions;

import java.util.List;
public record ValidationErrorResponse(List<String> errorMessageList) {
}
