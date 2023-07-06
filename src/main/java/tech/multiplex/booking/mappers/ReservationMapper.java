package tech.multiplex.booking.mappers;

import tech.multiplex.booking.domain.Reservation;
import tech.multiplex.booking.domain.Ticket;
import tech.multiplex.booking.enums.TicketType;
import tech.multiplex.booking.dto.ReservationSummaryDTO;

import java.math.BigDecimal;

public class ReservationMapper {

    public static ReservationSummaryDTO map(Reservation reservation) {

        BigDecimal totalPrice = reservation.getTickets().stream()
                .map(Ticket::getType).map(TicketType::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);

        return new ReservationSummaryDTO(reservation.getId(), reservation.getExpirationTime(), totalPrice);
    }
}
