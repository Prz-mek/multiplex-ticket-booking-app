package tech.multiplex.booking.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ReservationSummaryDTO(Long id, LocalDateTime expirationTime, BigDecimal totalPrice) {
}
