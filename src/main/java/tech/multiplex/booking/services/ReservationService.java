package tech.multiplex.booking.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ScreeningRepository screeningRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private SeatRepository seatRepository;

    @Value("${const.seat.forbiddenEmptySpaceBetween}")
    private Long forbiddenEmptySpaceBetween;

    @Value("${const.reservation.minTimeBeforeInMinutes}")
    private Long minTimeBeforeInMinutes;

    @Transactional
    public Long makeReservation(ReservationRequestDTO reservationRequestBody) {
        Long screeningId = reservationRequestBody.getScreeningId();
        Screening screening = getScreeningIfCanBeReserved(screeningId);
        List<TicketDTO> requestTickets = reservationRequestBody.getTickets();

        List<Seat> seats = getSeatsIfFree(screeningId, screening.getRoom().getId(), requestTickets);

        Reservation reservation = reservationRepository.save(new Reservation(screening, reservationRequestBody.getFirstName(),
                reservationRequestBody.getLastName(), screening.getStartTime().minusMinutes(minTimeBeforeInMinutes)));

        List<Ticket> tickets = seats.stream()
                .map(seat -> Ticket.builder()
                        .type(requestTickets.stream().filter(ticketDTO -> ticketDTO.getSeatId() == seat.getId())
                                .map(TicketDTO::getType).findAny().get())
                        .seat(seat)
                        .reservation(reservation)
                        .screening(screening)
                        .build()
                ).toList();

        ticketRepository.saveAll(tickets);

        return reservation.getId();
    }


    public ReservationSummaryDTO getReservationSummary(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationNotFoundException("Reservation with id: " + id + " does not exist"));
        return ReservationMapper.map(reservation);
    }


    // TODO do refactoring
    protected List<Seat> getSeatsIfFree(Long screeningId, Long roomId, List<TicketDTO> tickets) {
        if (tickets == null || tickets.isEmpty()) {
            throw new NoTicketsException("Reservation have to have tickets");
        }

        List<Long> reservedSeatsIds = tickets.stream().map(t -> t.getSeatId()).toList();
        boolean isChosenSeatsTaken = ticketRepository.existsByScreeningIdAndSeatIdIn(screeningId, reservedSeatsIds);
        if (isChosenSeatsTaken) {
            throw new SeatAlreadyReservedException("One or more seats are already reserved");
        }

        List<Long> seatsIdsOfCurrentReservation = tickets.stream().map(t -> t.getSeatId()).toList();
        List<Seat> seatsOfCurrentReservation = seatRepository.findAllById(seatsIdsOfCurrentReservation);
        List<Integer> rowsOfSeatsOfCurrentReservation = seatsOfCurrentReservation.stream()
                .map(s -> s.getRow()).distinct().toList();
        List<Seat> alreadyReservedSeatsInChosenRows = seatRepository.findAllNotAvailableSeatsByRowIn(screeningId, roomId, rowsOfSeatsOfCurrentReservation);

        alreadyReservedSeatsInChosenRows.addAll(seatsOfCurrentReservation);

        Map<Integer, List<Integer>> seatNumbersByRow = alreadyReservedSeatsInChosenRows.stream()
                .collect(Collectors.groupingBy(Seat::getRow, Collectors.mapping(Seat::getNumberInRow, Collectors.toList())));

        for (List<Integer> row : seatNumbersByRow.values()) {
            row.sort(Integer::compareTo);
            for (int i = 1; i < row.size(); i++) {
                if (row.get(i) - row.get(i - 1) == forbiddenEmptySpaceBetween + 1) {
                    throw new GapBetweenTakenSeatsIsForbiddenException("Gap is between taken seats is " + forbiddenEmptySpaceBetween);
                }
            }
        }

        return seatsOfCurrentReservation;
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
