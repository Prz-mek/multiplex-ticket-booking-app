package tech.multiplex.booking.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.multiplex.booking.domain.Screening;
import tech.multiplex.booking.domain.Seat;
import tech.multiplex.booking.domain.Ticket;
import tech.multiplex.booking.dto.ScreeningDTO;
import tech.multiplex.booking.dto.ScreeningDetailsDTO;
import tech.multiplex.booking.dto.SeatDTO;
import tech.multiplex.booking.exceptions.ScreeningNotFoundException;
import tech.multiplex.booking.mappers.ScreeningMapper;
import tech.multiplex.booking.mappers.SeatMapper;
import tech.multiplex.booking.repositories.ScreeningRepository;
import tech.multiplex.booking.repositories.SeatRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScreeningService {
    private final ScreeningRepository screeningRepository;
    private final SeatRepository seatRepository;

    public List<ScreeningDTO> getScreeningsByDate(LocalDateTime from, LocalDateTime to) {
        return ScreeningMapper.map(screeningRepository.findAllByStartTimeBetweenOrderByMovieTitleAscStartTime(from, to));
    }

    public ScreeningDetailsDTO getScreeningDetails(Long id) {
        Screening screening = screeningRepository.findById(id).orElseThrow(() ->
                new ScreeningNotFoundException("Screening with " + id + " doesn't exist"));

        List<SeatDTO> availableSeats = getAvailableSeats(screening);

        return new ScreeningDetailsDTO(screening.getId(), screening.getMovie().getTitle(),
                screening.getStartTime(), screening.getRoom().getName(), availableSeats);
    }

    protected List<SeatDTO> getAvailableSeats(Screening screening) {
        List<Seat> availableSeats = seatRepository
                .findAvailableSeatsByScreeningIdAndRoomId(screening.getId(), screening.getRoom().getId());
        return SeatMapper.map(availableSeats);
    }
}
