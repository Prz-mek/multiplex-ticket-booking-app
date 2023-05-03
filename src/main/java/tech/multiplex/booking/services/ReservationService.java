package tech.multiplex.booking.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tech.multiplex.booking.domain.Reservation;
import tech.multiplex.booking.domain.Screening;
import tech.multiplex.booking.domain.Seat;
import tech.multiplex.booking.domain.Ticket;
import tech.multiplex.booking.dto.ReservationRequestDTO;
import tech.multiplex.booking.dto.ReservationSummaryDTO;
import tech.multiplex.booking.dto.TicketDTO;
import tech.multiplex.booking.exceptions.*;
import tech.multiplex.booking.mappers.ReservationMapper;
import tech.multiplex.booking.repositories.ReservationRepository;
import tech.multiplex.booking.repositories.ScreeningRepository;
import tech.multiplex.booking.repositories.SeatRepository;
import tech.multiplex.booking.repositories.TicketRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private ReservationRepository reservationRepository;
    private ScreeningRepository screeningRepository;
    private TicketRepository ticketRepository;
    private SeatRepository seatRepository;

    @Value("${const.seat.forbiddenEmptySpaceBetween}")
    private Long forbiddenEmptySpaceBetween;

    @Value("${const.reservation.minTimeBeforeInMinutes}")
    private Long minTimeBeforeInMinutes;

    @Transactional
    public Long makeReservation(ReservationRequestDTO reservationRequestBody) {
        Long screeningId = reservationRequestBody.getScreeningId();
        Screening screening = screeningRepository.findById(screeningId)
                .orElseThrow(() -> new ScreeningNotFoundException("Screening with id: " + screeningId + "doesn't exist"));
        List<TicketDTO> tickets = reservationRequestBody.getTickets();
        List<Seat> takenSeats = getSeatsIfFree(screeningId, screening.getRoom().getId(), tickets);

        Reservation reservation = reservationRepository.save(new Reservation(screening, reservationRequestBody.getFirstName(),
                reservationRequestBody.getLastName(), screening.getStartTime().minusMinutes(minTimeBeforeInMinutes)));

        List<Ticket> validatedTickets = takenSeats.stream()
                .map(seat -> new Ticket(
                        tickets.stream().filter(ticketDTO -> ticketDTO.getSeatId() == seat.getId())
                                .map(TicketDTO::getType).findAny().get(),
                        seat,
                        reservation,
                        screening)).toList();

        ticketRepository.saveAll(validatedTickets);

        return reservation.getId();
    }


    public ReservationSummaryDTO getReservationSummary(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationNotFoundException("Reservation with id: " + id + "does not exist"));
        return ReservationMapper.map(reservation);
    }

    protected List<Seat> getSeatsIfFree(Long screeningId, Long roomId, List<TicketDTO> tickets) {
        if (tickets == null || tickets.isEmpty()) {
            throw new NoTicketsException("Reservation have to have tickets");
        }
        List<Seat> availableSeats = getAvailableSeats(screeningId, roomId);
        List<Long> availableSeatIds = availableSeats.stream().map(Seat::getId).toList();
        boolean chosenSeatsAreFree = tickets.stream().allMatch(ticket -> availableSeatIds.contains(ticket.getSeatId()));
        if (!chosenSeatsAreFree) {
            throw new SeatsAreTakenException("One or more seats are already reserved");
        }

        // TODO do refactoring
        List<Seat> reservedSeats = tickets.stream().map(ticket -> {
            return availableSeats.stream().filter(seat -> seat.getId() == ticket.getSeatId()).findFirst().get();
        }).toList();

        List<Seat> takenSeats = getTakenSeats(screeningId, roomId);
        takenSeats.addAll(reservedSeats);

        Map<Integer, List<Integer>> seatNumbersByRow = takenSeats.stream()
                .collect(Collectors.groupingBy(Seat::getRow, Collectors.mapping(Seat::getNumberInRow, Collectors.toList())));

        for (List<Integer> row : seatNumbersByRow.values()) {
            row.sort(Integer::compareTo);

            for (int i = 1; i < row.size(); i++) {
                if (row.get(i) - row.get(i - 1) == forbiddenEmptySpaceBetween + 1) {
                    throw new GapBetweenTakenSeatsIsForbiddenException("Gap is between taken seats is" + forbiddenEmptySpaceBetween);
                }
            }
        }

        return reservedSeats;
    }

    protected List<Seat> getAvailableSeats(Long screeningId, Long roomId) {
        List<Ticket> tickets = ticketRepository.findAllByScreeningId(screeningId);
        List<Seat> takenSeats = tickets.stream().map(elem -> elem.getSeat()).collect(Collectors.toList());
        List<Seat> allSeats = seatRepository.findAllByRoomId(roomId);
        List<Seat> availableSeats = allSeats.stream().filter(e -> !takenSeats.contains(e)).collect(Collectors.toList());
        return availableSeats;
    }

    protected List<Seat> getTakenSeats(Long screeningId, Long roomId) {
        List<Ticket> tickets = ticketRepository.findAllByScreeningId(screeningId);
        List<Seat> takenSeats = tickets.stream().map(elem -> elem.getSeat()).collect(Collectors.toList());
        return takenSeats;
    }

    protected Screening getScreeningIfCanBeReserved(Long id) {
        Screening screening = screeningRepository.findById(id)
                .orElseThrow(() -> new ScreeningNotFoundException("Screening with " + id + " doesn't exist"));
        LocalDateTime lastMoment = screening.getStartTime().minusMinutes(minTimeBeforeInMinutes);
        if (LocalDateTime.now().isAfter(lastMoment)) {
            throw new ReservationTimePassedException("Too late for reservation");
        }

        return screening;
    }
}
