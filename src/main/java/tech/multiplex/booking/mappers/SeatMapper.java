package tech.multiplex.booking.mappers;

import tech.multiplex.booking.domain.Seat;
import tech.multiplex.booking.dto.SeatDTO;

import java.util.List;

public class SeatMapper {

    public static SeatDTO map(Seat seat) {
        return new SeatDTO(seat.getId(), seat.getRow(), seat.getNumberInRow());
    }

    public static List<SeatDTO> map(List<Seat> seats) {
        return seats.stream().map(SeatMapper::map).toList();
    }
}
