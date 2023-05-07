package tech.multiplex.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import tech.multiplex.booking.enums.TicketType;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class TicketDTO {
    private Long seatId;
    private TicketType type;
}
